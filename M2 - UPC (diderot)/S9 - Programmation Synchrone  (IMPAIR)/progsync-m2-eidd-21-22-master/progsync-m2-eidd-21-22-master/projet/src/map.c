#include "map.h"

#include <string.h>

#include "mymath.h"
#include "cutils.h"

map_t *map;

/*
 * =========================================================================
 * Geometry
 * =========================================================================
 */

/** Euclidian distance betwen two points (xa,ya) and (xb,yb) */
float distance(float xa, float ya, float xb, float yb)
{
  /* use of math.h function */
  return hypotf(xa - xb, ya - yb);
}

/** Convert angle from radian to degree */
float todegree(float a)
{
  return (a * 180.0) / M_PI;
}

/** Convert angle from degree to radian */
float toradian(float a)
{
  return (a * M_PI) / 180.0;
}

/** Get the cadran of the angle (in degree) */
int tocadran(float a) {
  if ((0 <= a && a < 90) ||
      (-360 <= a && a < -270))
    return 1;
  if ((90 <= a && a < 180) ||
      (-270 <= a && a < -180))
    return 2;
  if ((180 <= a && a < 270) ||
      (-180 <= a && a < -90))
    return 3;
  return 4;
}

/** Angle (in degree) of a segment */
float lineAngle(float x1, float y1, float x2, float y2)
{
  /* use of math.h function for arctan */
  return todegree(atan2(y2-y1,x2-x1));
}

/** Angle inside an arc delimited by (from,to) */
bool isInArc(float a, float from, float to)
{
  if (from < to) {
    // trigo direction
    return (from <= a && a <= to) || (from <= (a+360) && (a+360) <= to);
  }
  else {
    // clock direction
    return (to <= a && a <= from) || (to <= (a+360) && (a+360) <= from);
  }
}

/** Angles in ordred inside an arc delimited by (from,to) */
bool fourAnglesInOrder(float from,float x,float y, float to)
{
  if (from < to) {
    // trigo direction
    return
      (from<=x && x<=y && y<=to) ||
      (from<=x+360 && x+360<=y+360 && y+360<=to) ||
      (from<=x && x<=y+360 && y+360<=to) ||
      (from<=x+360 && x+360<=y && y<=to);
  } else  {
    // clock direction
    return (to <= y && y<=x && x <= from) ||
      (to <= y+360 && y+360<=x+360 && x+360 <= from) ||
      (to <= y+360 && y+360<=x && x <= from) ||
      (to <= y && y<=x+360 && x+360 <= from);
  }
}

/** Product of two vectors for directions */
float dirProd(float dir1x, float dir1y, float dir2x, float dir2y)
{
  return dir1x * dir2x + dir1y * dir2y;
}

float dirVProd(float dir1x, float dir1y, float dir2x, float dir2y)
{
  return dir1x * dir2y - dir1y * dir2x;
}

/** Size of a direction vector */
float dirNorm(float dir1x, float dir1y)
{
  /* use of math.h function */
  return hypot(dir1x, dir1y);
}

/** Cos of directions */
float dirCos(float dir1x, float dir1y, float dir2x, float dir2y)
{
  return dirProd(dir1x, dir1y, dir2x, dir2y)
    / (dirNorm(dir1x, dir1y) * dirNorm(dir2x, dir2y));
}

/** Project point (x,y) on line d(p1,p2) and
 *  return result on (px, py)
 */
void
dirProjPoint( /* IN  */ float x, float y, position_t * p1, position_t * p2,
              /* OUT */ float *px, float *py)
{
  //compute direction vector for d(p1, p2)
  float           dir_x = p2->x - p1->x;
  float           dir_y = p2->y - p1->y;
  float           dirn = dirNorm(dir_x, dir_y);

  //compute P1 - H
  float           p1h = ((x - p1->x) * dir_x + (y - p1->y) * dir_y) / dirn;

  //compute h
  (*px) = p1->x + (p1h * dir_x) / dirn;
  (*py) = p1->y + (p1h * dir_y) / dirn;

  return;
}

/*
 * =========================================================================
 * Parsing
 * =========================================================================
 */

typedef void (*map_segment_line_loader)(FILE *,       /* file to read */
                                        const char *, /* file name */
                                        void *,       /* pointer to the element
                                                         to be filled */
                                        size_t);    /* line number */

void map_load_segment(FILE *f,
                      const char *filename,
                      map_segment_line_loader loader,
                      const char *segment_name,
                      void *psegment,
                      int *segment_size,
                      size_t element_size,
                      size_t segment_max_size,
                      size_t *pline) {
  assert (f);
  assert (filename);
  assert (segment_name);
  assert (psegment);
  assert (segment_size);
  assert (pline);

  char kword[15], **segment = (char **)psegment;
  int rcode;

  /* read until "segment_name" encountered */
  while (true) {
    rcode = fscanf(f, "%14s", kword);
    (*pline)++;

    if ((rcode == EOF) || (strcmp(kword, "end") == 0))
      log_fatal("[map %s] could not find segment %s\n", filename, segment_name);

    if (strcmp(kword, segment_name) == 0)
      break;
  }

  /* "segment_name" has been read, now read item number */
  rcode = fscanf(f, "%d", segment_size);
  if (rcode == EOF)
    log_fatal("[map %s] reached end of file while looking"
              " for element count in segment %s (line %zu)\n",
              filename, segment_name, *pline);
  if ((*segment_size < 0) || (*segment_size > segment_max_size))
    log_fatal("[map %s] too many (%d) elements in "
              "segment %s (should be <= %d) (line %zu)\n",
              filename, *segment_size, segment_name, segment_max_size, *pline);
  (*pline)++;

  /* allocate enough elements */
  *segment = calloc(*segment_size, element_size);
  assert(*segment);

  log_info("[map %s] starting to read segment %s of size %zu\n",
      filename, segment_name, *segment_size);
  for (size_t i = 0; i < *segment_size; i++, (*pline)++)
    loader(f, filename, &(*segment)[i * element_size], *pline);
  log_info("[map %s] finished reading segment %s\n", filename, segment_name);
}

void rd_segment_line_loader(FILE *f,
                            const char *filename,
                            void *p,
                            size_t line) {
  assert (f);
  assert (filename);
  assert (p);

  char kword[15];
  road_t *rd = (road_t *)p;
  int rcode = fscanf(f, "%14s", kword);

  if (rcode == EOF)
    log_fatal("[map %s] 'line|arc' expected (line %zu)\n", filename, line);

  if (strcmp(kword, "line") == 0) {
    int nbdir = 0;
    /* read "line <dir> <max_speed> <startX> <startY> <endX> <endY>" */
    rcode = fscanf(f, "%d %d %f %f %f %f",
                   &nbdir, &rd->max_speed,
                   &rd->u.line.startp.x, &rd->u.line.startp.y,
                   &rd->u.line.endp.x, &rd->u.line.endp.y);
    if ((rcode == EOF) ||
        (nbdir < 1) ||
        (nbdir > 2) ||
        (rd->max_speed <= 0) ||
        (rd->max_speed >= SPEED_MAX) ||
        (rd->u.line.startp.x <= MIN_X) ||
        (rd->u.line.startp.x >= MAX_X) ||
        (rd->u.line.endp.x <= MIN_X) ||
        (rd->u.line.endp.x >= MAX_X) ||
        (rd->u.line.startp.y <= MIN_Y) ||
        (rd->u.line.startp.y >= MAX_Y) ||
        (rd->u.line.endp.y <= MIN_Y) ||
        (rd->u.line.endp.y >= MAX_Y))
      log_fatal("[map_read %s]: 'line' format error (line %zu)\n",
                filename, line);

    rd->color = COL_ROADLINE;
    rd->kind = (nbdir == 1) ? RD_LINE_1 : RD_LINE_2;
    rd->dir_x = (rd->u.line.endp.x - rd->u.line.startp.x);
    rd->dir_y = (rd->u.line.endp.y - rd->u.line.startp.y);
  } else if (strcmp(kword, "arc") == 0) {
    int nbdir = 0;
    /* read "arc <dir> <max_speed> <centerX> <centerY>
       <radius> <start_angle> <end_angle>" */
    rcode = fscanf(f, "%d %d %f %f %f %f %f",
                   &nbdir, &rd->max_speed,
                   &rd->u.arc.center.x, &rd->u.arc.center.y,
                   &rd->u.arc.radius,
                   &rd->u.arc.start_angle,
                   &rd->u.arc.end_angle);
    if ((rcode == EOF) ||
        (nbdir != 1) ||
        (rd->max_speed <= 0) ||
        (rd->max_speed >= SPEED_MAX) ||
        (rd->u.arc.center.x <= MIN_X) ||
        (rd->u.arc.center.x >= MAX_X) ||
        (rd->u.arc.center.y <= MIN_Y) ||
        (rd->u.arc.center.y >= MAX_Y) ||
        (rd->u.arc.radius <= 0) ||
        (rd->u.arc.start_angle < -180.0) ||
        (rd->u.arc.start_angle >= 360.0) ||
        (rd->u.arc.end_angle < -180.0) ||
        (rd->u.arc.end_angle >= 360.0))
      log_fatal("[map %s]: 'arc' format error (line %zu)\n",
                filename, line);
    rd->color = COL_ROADLINE;
    rd->kind = RD_ARC;
    rd->dir_x = 0.0;
    rd->dir_y = (rd->u.arc.start_angle < rd->u.arc.end_angle) ? -1.0 : 1.0;
  } else {
    log_fatal("[map %s] unknown road type %s (line %zu)\n",
              filename, kword, line);
  }
}

void wayp_segment_line_loader(FILE *f,
                              const char *filename,
                              void *p,
                              size_t line) {
  assert (f);
  assert (filename);
  assert (p);

  int rcode;
  waypoint_t *wp = (waypoint_t *)p;

  /* read <road> <X> <Y> */
  rcode = fscanf(f, "%d %f %f", &wp->road, &wp->position.x, &wp->position.y);
  if ((rcode == EOF) ||
      (wp->road < 0) ||
      (wp->road >= map->road_sz) ||
      (wp->position.x <= MIN_X) ||
      (wp->position.x >= MAX_X) ||
      (wp->position.y <= MIN_Y) ||
      (wp->position.y >= MAX_Y)) {
    log_fatal("[map %s] waypoint format error (line %zu)\n", filename, line);
  }
}

void tl_segment_line_loader(FILE *f,
                            const char *filename,
                            void *p,
                            size_t line) {
  assert (f);
  assert (filename);
  assert (p);

  int rcode;
  tlight_t *tl = (tlight_t *)p;

  /* read <road> <X> <Y> <delayR> <delayA> <delayG> <dephase> */
  rcode = fscanf(f, "%d %f %f %d %d %d %d",
                 &tl->road,
                 &tl->tl.ptl_pos.x, &tl->tl.ptl_pos.y,
                 &tl->tl.ptl_red, &tl->tl.ptl_amber, &tl->tl.ptl_green,
                 &tl->tl.ptl_phase);
  if ((rcode == EOF) ||
      (tl->road < 0) ||
      (tl->road >= map->road_sz) ||
      (tl->tl.ptl_pos.x <= MIN_X) ||
      (tl->tl.ptl_pos.x >= MAX_X) ||
      (tl->tl.ptl_pos.y <= MIN_Y) ||
      (tl->tl.ptl_pos.y >= MAX_Y))
    log_fatal("[map %s] traffic light format error (line %zu)\n",
              filename, line);
}

void st_segment_line_loader(FILE *f,
                            const char *filename,
                            void *p,
                            size_t line) {
  assert (f);
  assert (filename);
  assert (p);

  int rcode;
  stop_t *st = (stop_t *)p;

  /* read <road> <tlight> <X> <Y> */
  rcode = fscanf(f, "%d %d %f %f",
                 &st->road, &st->sema,
                 &st->position.x, &st->position.y);
  if ((rcode == EOF) ||
      (st->road < 0) ||
      (st->road >= map->road_sz) ||
      (st->sema < 0) ||
      (st->sema >= map->tlight_sz) ||
      (st->position.x <= MIN_X) ||
      (st->position.x >= MAX_X) ||
      (st->position.y <= MIN_Y) ||
      (st->position.y >= MAX_Y))
    log_fatal("[map %s] stop format error (line %zu)\n", filename, line);
}

void obst_segment_line_loader(FILE *f,
                              const char *filename,
                              void *p,
                              size_t line) {
  assert (f);
  assert (filename);
  assert (p);

  int rcode;
  obst_t *obst = (obst_t *)p;

  rcode = fscanf(f, "%f %f %f %f", &obst->pot_pos.x, &obst->pot_pos.y,
                 &obst->pot_since, &obst->pot_till);
  if ((rcode == EOF) ||
      (obst->pot_pos.x <= MIN_X) ||
      (obst->pot_pos.x >= MAX_X) ||
      (obst->pot_pos.y <= MIN_Y) ||
      (obst->pot_pos.y >= MAX_Y))
    log_fatal("[map %s] obstacle format error (line %zu)\n", filename, line);
}

void iti_segment_line_loader(FILE *f,
                             const char *filename,
                             void *p,
                             size_t line) {
  assert (f);
  assert (filename);
  assert (p);

  char kword[15];
  iti_t *iti = (iti_t *)p;
  int rcode = fscanf(f, "%14s %f", kword, &iti->param);

  if (strcmp(kword, "go") == 0) {
    iti->act = Globals__Go;
    if ((rcode == EOF) || (iti->param <= 0.f) || (iti->param > SPEED_MAX))
      log_fatal("[map %s] itinerary format error (action Go, line %zu)\n",
                filename, line);
  } else if (strcmp(kword, "turn") == 0) {
    iti->act = Globals__Turn;
    if ((rcode == EOF) || (iti->param < -180.0) || (iti->param >= 360.0))
      log_fatal("[map %s] itinerary format error (action Turn, line %zu)\n",
                filename, line);
  } else if (strcmp(kword, "stop") == 0) {
    iti->act = Globals__Stop;
    iti->param = 0.f;           /* Dummy */
  } else {
    log_fatal("[map %s] itinerary format error (unknown action %s, line %zu)\n",
              filename, kword, line);
  }
}

void map_read_string_line(FILE *f, const char *filename,
                          const char *expected_kw, char *buff, size_t *pline) {
  char kword[15];
  int rcode = fscanf(f, "%14s %249s", kword, buff);
  if ((rcode == EOF) || (strcmp(kword, expected_kw) != 0))
    log_fatal("[map %s] could not read %s (line %zu)\n",
              filename, expected_kw, *pline);
  if (buff[0] != '"' || buff[strlen(buff) - 1] != '"')
    log_fatal("[map %s] string after %s should be between double quotes"
              " (line %zu)\n",
              filename, expected_kw, *pline);
  (*pline)++;

  /* Remove surrounding double quotes. */
  memmove(buff, buff + 1, strlen(buff) - 2);
  buff[strlen(buff) - 2] = 0;

  log_info("[map %s] read %s: %s\n", filename, expected_kw, buff);
}

void map_load(const char *filename) {
  map = malloc(sizeof *map);
  assert(map);
  bzero(map, sizeof *map);

  size_t line = 1;
  FILE *f = fopen(filename, "r");
  if (!f)
    log_fatal("[map %s] could not open file\n", filename);

  map_read_string_line(f, filename, "map", map->name, &line);
  map_read_string_line(f, filename, "graphics", map->graphics, &line);
  map_read_string_line(f, filename, "guide", map->guide, &line);

  char kword[15];
  int rcode = fscanf(f, "%14s %f %f %f", kword,
                     &map->init_phase.ph_pos.x,
                     &map->init_phase.ph_pos.y,
                     &map->init_phase.ph_head);
  if ((rcode == EOF) || (strcmp(kword, "init") != 0))
    log_fatal("[map %s] could not read init (line %zu)\n", filename, line);
  line++;
  log_info("[map %s] read init: x = %f, y = %f, head = %f\n",
      filename,
      map->init_phase.ph_pos.x,
      map->init_phase.ph_pos.y,
      map->init_phase.ph_head);

  /* Read the roads. */
  map_load_segment(f, filename, rd_segment_line_loader, "rd",
                   &map->road_arr, &map->road_sz, sizeof *map->road_arr,
                   MAX_ROAD_COUNT, &line);

  /* Read the waypoints. */
  map_load_segment(f, filename, wayp_segment_line_loader, "wp",
                   &map->wayp_arr, &map->wayp_sz, sizeof *map->wayp_arr,
                   MAX_WAYP_COUNT, &line);

  /* Read the traffic lights. */
  map_load_segment(f, filename, tl_segment_line_loader, "tl",
                   &map->tlight_arr, &map->tlight_sz, sizeof *map->tlight_arr,
                   MAX_TL_COUNT, &line);

  /* Read the stops. */
  map_load_segment(f, filename, st_segment_line_loader, "st",
                   &map->stop_arr, &map->stop_sz, sizeof *map->stop_arr,
                   MAX_STOP_COUNT, &line);

  /* Read the obstacles. */
  map_load_segment(f, filename, obst_segment_line_loader, "obst",
                   &map->obst_arr, &map->obst_sz, sizeof *map->obst_arr,
                   MAX_OBST_COUNT, &line);

  /* Read the obstacles. */
  map_load_segment(f, filename, iti_segment_line_loader, "iti",
                   &map->iti_arr, &map->iti_sz, sizeof *map->iti_arr,
                   MAX_ITI_COUNT, &line);

  /* Close file and return. */
  fclose(f);
}

void map_destroy() {
  if (!map)
    return;

  /* Fields have been initialized to NULL, it is safe to call free() on them. */
  free(map->road_arr);
  free(map->wayp_arr);
  free(map->tlight_arr);
  free(map->stop_arr);
  free(map->obst_arr);
  free(map);
  map = NULL;
}

void Map__read_obstacles_step(Map__read_obstacles_out *o) {
  /* TODO very inefficient */
  memcpy(o->obst, map->obst_arr, map->obst_sz * sizeof *o->obst);

  /* The map file might contain fewer obstacles than Globals__obstnum. */
  for (size_t i = map->obst_sz; i < MAX_OBST_COUNT; i++) {
    o->obst[i].pot_pos.x = 0.f;
    o->obst[i].pot_pos.y = 0.f;
    o->obst[i].pot_since = -1.f;
    o->obst[i].pot_till = -1.f;
  }
}

void Map__read_itinerary_step(Map__read_itinerary_out *o) {
  /* TODO very inefficient */
  memcpy(o->iti, map->iti_arr, map->iti_sz * sizeof *o->iti);

  /* The map file might contain fewer itinerary steps than Globals__itinum. */
  for (size_t i = map->iti_sz; i < MAX_ITI_COUNT; i++) {
    o->iti[i].act = Globals__Stop;
    o->iti[i].param = 0.f;
  }
}

void Map__read_traffic_lights_step(Map__read_traffic_lights_out *o) {
  assert (map->tlight_sz <= Globals__trafnum);
  for (size_t i = 0; i < map->tlight_sz; i++)
    memcpy(&o->tlights[i], &map->tlight_arr[i].tl, sizeof *map->tlight_arr);
  for (size_t i = map->tlight_sz; i < MAX_TL_COUNT; i++)
    o->tlights[i] = (Globals__param_tlight){ {-100, -100}, 0, 0, 0, 0 };
}

bool colors_equal(const Globals__color *a, const Globals__color *b) {
  return a->red == b->red && a->green == b->green && a->blue == b->blue;
}

bool isOnRoadLine1(road_t *rd, float x, float y,
                   Globals__color *col, double *d, float *dir_x, float *dir_y) {
  //test that may be inside road area
  // -compute projection on line
  float                   px = 0.0;
  float                   py = 0.0;
  dirProjPoint(x, y, &(rd->u.line.startp), &(rd->u.line.endp), &px, &py);
  //-compare with ends of the segment
  if ((px <= fmax(rd->u.line.startp.x, rd->u.line.endp.x)+EPS) &&
      (px >= fmin(rd->u.line.startp.x, rd->u.line.endp.x)-EPS) &&
      (py <= fmax(rd->u.line.startp.y, rd->u.line.endp.y)+EPS) &&
      (py >= fmin(rd->u.line.startp.y, rd->u.line.endp.y)-EPS))
    //the projection is inside, compute distance to rd
    (*d) = distance(x, y, px, py);
  else
    //the projection is outside the segment
    // let us test the extremities:
    (*d) = fmin(distance(x,y,rd->u.line.startp.x,rd->u.line.startp.y),
                distance(x,y,rd->u.line.endp.x,rd->u.line.endp.y));

  //-compare with the width of the road
  if ((*d) >= RD_SIZE_HALF_WIDTH) {
    log_debug("[geometry] (%.2f, %.2f) is too far from road %p (dist. %.2f)!\n",
              x, y, rd, *d);
    return false;
  }

  (*col) = rd->color;
  (*dir_x) = rd->u.line.endp.x - rd->u.line.startp.x;
  (*dir_y) = rd->u.line.endp.y - rd->u.line.startp.y;

  if ((*d) < RD_SIZE_LINE) {
    //distance less than width of the road line,
    //then on road with one color
    //log_debug("Position on road line: %f!\n", *d);
  }
  else {
    //otherwise, compute the side of the road
    // left or right using direction(x, y)->(px, py)
    double          dirPX = px - x;     /* -b where b = x1 - x2 */
    double          dirPY = py - y;     /* a  where a = y2 - y1 */
    double          sinDir = dirVProd(*dir_x, *dir_y, dirPX, dirPY);
    if (sinDir < 0) {
      //on left
      (*col) = COL_ROADLEFT;
#ifndef MAP_BMP
      if ((*d) <= RD_SIZE_LINE2)
        col->green = (unsigned int)(255.*((*d)-1.0));
#endif
    } else {
      //on right
      (*col) = COL_ROADRIGHT;
#ifndef MAP_BMP
      if ((*d) <= RD_SIZE_LINE2)
        col->red = (unsigned int)(255.*((*d)-1.0));
#endif
    }
    //log_debug("Position on road: %f!\n", *d);
  }
  return true;
}

bool isOnRoadLine2(road_t *rd, float x, float y,
                   Globals__color *col, double *d, float *dir_x, float *dir_y) {
  /* TODO missing from original project */
  return false;
}

bool isOnRoadArc(/* IN  */
                 road_t *rd, float x, float y,
                 /* OUT */
                 Globals__color *col, double *d,
                 float *dir_x, float *dir_y)
{
  //center
  double          cx = rd->u.arc.center.x;
  double          cy = rd->u.arc.center.y;

  //compute angle(in degrees) from center
  double          a = lineAngle(cx, cy, x, y);

  //compute signed distance to the circle c
  double     dist_c = distance(cx, cy, x, y) - rd->u.arc.radius;

  if (isInArc(a, rd->u.arc.start_angle,rd->u.arc.end_angle)) {
    //within the angle
    (*d) = fabs(dist_c);
  }
  else {
    //Point outside road angles,
    //  consider the distance to extremities
    (*d) = fmin(distance(x,y,
                         cx+rd->u.arc.radius*cos(toradian(rd->u.arc.start_angle)),
                         cy+rd->u.arc.radius*sin(toradian(rd->u.arc.start_angle))),
                distance(x,y,
                         cx+rd->u.arc.radius*cos(toradian(rd->u.arc.end_angle)),
                         cy+rd->u.arc.radius*sin(toradian(rd->u.arc.end_angle))));
  }

  if ((*d) >= RD_SIZE_HALF_WIDTH) {
    //log_debug("Position too far from road: %f!\n", (*d));
    return false;
  }

  (*col) = rd->color;

  //direction is normal to d(c, (x, y))
  //its sign depends on rotation
  if (rd->u.arc.start_angle < rd->u.arc.end_angle){//counterclockwise
    (*dir_x) = cy - y;
    (*dir_y) = x - cx;
  }
  else {//clockwise
    (*dir_x) = y - cy;           /* a where a = y2 - y1 */
    (*dir_y) = cx - x;           /* b where b = x1 - x2 */
  }

  if ((*d) < RD_SIZE_LINE) {
    //distance less than width of the road line,
    //then on road with one color
    //log_debug("Point on road line: %f!\n", *d);
  } else {
    //otherwise, compute the side of the road
    // left or right using the sense(trigo or clock) of the road
    if (((rd->u.arc.start_angle < rd->u.arc.end_angle) //trigo dir
         && dist_c < 0) ||
        ((rd->u.arc.start_angle > rd->u.arc.end_angle) // clock dir
         && dist_c > 0)) {
      (*col) = COL_ROADLEFT;
#ifndef MAP_BMP
      if ((*d) <= RD_SIZE_LINE2)
        col->green = (unsigned int)(255.*((*d)-1.0));
#endif
    } else {
      (*col) = COL_ROADRIGHT;
#ifndef MAP_BMP
      if ((*d) <= RD_SIZE_LINE2)
        col->red = (unsigned int)(255.*((*d)-1.0));
#endif
    }
  }
  //log_debug("Point on road: %f!\n", *d);
  return true;
}

int isOnTLight(int x, int y, int rd, float dir_x, float dir_y)
{
  if (map->tlight_arr == NULL ||
      map->tlight_sz <= 0)
    return -1;
  //no traffic light

  for (int i = 0; i < map->tlight_sz; i++) {
    tlight_t *tl = &map->tlight_arr[i];
    if (tl->road != rd)
      continue;
    double          d = distance(x, y, tl->tl.ptl_pos.x, tl->tl.ptl_pos.y);

   /* double        cosdir = dirCos(tl->tl.ptl_pos.y - y, x - tl->tl.ptl_pos.x,
                                    dir_x, dir_y); */    //MS
   double           cosdir = dirCos(tl->tl.ptl_pos.x-x, tl->tl.ptl_pos.y-y,
                                    dir_x, dir_y);     //EA
    if (d < TL_VIEW && cosdir > TL_COSDIR)
      return i;
  }
  return -1;
}

bool isAfterStop(int x, int y, int rid, float dir_x, float dir_y, int* tl)
{
  (*tl) = -1;

  if (map->stop_arr == NULL ||
      map->stop_sz <= 0)
    return false;
  //no stop points

  for (int i = 0; i < map->stop_sz; i++) {
    stop_t *sp = &map->stop_arr[i];
    if (sp->road != rid)
      continue;
    // check the distance to the point
    road_t *rd = &map->road_arr[sp->road];
    if (rd->kind == RD_LINE_1 ||
        rd->kind == RD_LINE_2) {
      // line
      // - compute projection on roadline
      float px = 0.0;
      float py = 0.0;
      dirProjPoint(x, y, &(rd->u.line.startp), &(rd->u.line.endp), &px, &py);
      // - compare with the bounds of the stop point
      double dsp = distance(px, py, sp->position.x, sp->position.y);
      double dir_x = px - sp->position.x;
      double dir_y = py - sp->position.y;
      if (dsp >= (RD_SIZE_STOP-EPS) &&
          dsp <= (STP_AFTER+RD_SIZE_STOP+EPS) &&
          (dirProd(rd->dir_x, rd->dir_y, dir_x, dir_y) >= 0))
        {
          log_debug("[geometry] (%d, %d) is after stop %d (dist %.2f)\n",
                    x, y, i, dsp);
          (*tl) = sp->sema;
          return true;
        }
      else
        {
          log_debug("Position too far/not AFTER point on line: %lf!\n", dsp);
          log_debug("rd->dir(%lf,%lf) <> dir(%lf,%lf)\n", rd->dir_x, rd->dir_y,
                    dir_x, dir_y);
        }
    }
    else {
      // arc
      // -compute angle of point in degrees
      double apoint = lineAngle(rd->u.arc.center.x, rd->u.arc.center.y,
                                sp->position.x, sp->position.y);
      double apos = lineAngle(rd->u.arc.center.x, rd->u.arc.center.y, x, y);
      double dsp = toradian(fabs(apoint - apos))*rd->u.arc.radius;
      if (dsp >= (RD_SIZE_STOP-EPS) &&
          dsp <= (STP_AFTER+RD_SIZE_STOP+EPS)) {
        // - check that the direction is AFTER
        if (fourAnglesInOrder(rd->u.arc.start_angle,
                              apoint, apos,
                              rd->u.arc.end_angle))
            {
              log_debug("Position AFTER point on arc: %lf!\n", apos);
              (*tl) = sp->sema;
              return true;
            }
          else
            {
              log_debug("Position too far/not AFTER point on arc: %lf!\n", apos);
              return false;
            }
      }
      else {
        log_debug("Position too far/not AFTER point on arc: %lf degrees!\n", apos);
      }
    }
  }
  return false;
}

bool
isPositionOnPoint(float x, float y, road_t* rd, position_t* p, double pWidth)
{
  if (rd->kind == RD_LINE_1 ||
      rd->kind == RD_LINE_2) { // line
    // - compute projection on road line
    float           px = 0.0;
    float           py = 0.0;
    position_t      startp = {0, 0};
    position_t      endp = {0, 0};

    startp = (rd->u.line.startp);
    endp = (rd->u.line.endp);
    dirProjPoint(x, y, &(startp), &(endp), &px, &py);
    // - compare with the bounds of the waypoint
    double          dwp = distance(px, py, p->x, p->y);
    if (dwp <= pWidth) {
      log_debug("Position near point on line: %lf!\n", dwp);
      return true;
    }
    else {
      //log_debug("Position too far from point on line: %lf!\n", dwp);
      return false;
    }
  }
  else {
    // arc
    // - compute angle of point in degrees
    double apoint = lineAngle(rd->u.arc.center.x, rd->u.arc.center.y, p->x, p->y);
    double apos = lineAngle(rd->u.arc.center.x, rd->u.arc.center.y, x, y);
    double dwp = toradian(fabs(apoint - apos))*rd->u.arc.radius;
    if (dwp <= pWidth) {
      log_debug("Position near point on arc: %lf!\n", dwp);
      return true;
    }
    else {
      log_debug("Position too far from point on arc: %lf!\n", dwp);
      return false;
    }
  }
}

Globals__color getColorPoint(int rid, float x, float y) {
  // first go through waypoints
  log_debug("[geometry] looking for waypoints at (%.2f, %.2f) on road %d\n",
            x, y, rid);
  for (int i = 0; i < map->wayp_sz; i++) {
    waypoint_t *wp = &map->wayp_arr[i];
    if (wp->road != rid)
      {
        log_debug("Waypoint not on road %d!\n", rid);
        continue;
      }
    road_t *rd = &map->road_arr[wp->road];
    // waitpoints are ruban
    if (isPositionOnPoint(x, y, rd, &(wp->position), RD_SIZE_WAYPOINT)) {
      return COL_WAYPOINT;
      //one waypoint by position
    }
  }
  log_debug("[geometry] finished walking waypoints\n");

  // then go to stop points
  log_debug("[geometry] looking for stops at (%.2f, %.2f) on road %d\n",
            x, y, rid);
  for (int i = 0; i < map->stop_sz; i++) {
    stop_t *sp = &map->stop_arr[i];
    if (sp->road != rid) {
      log_debug("Stop not on road %d!\n", rid);
      continue;
    }
    road_t *rd = &map->road_arr[sp->road];
    // stop points are ruban
    if (isPositionOnPoint(x, y, rd, &sp->position, RD_SIZE_STOP)) {
      log_debug("[geometry] (%.2f, %.2f) at stop %d\n", x, y, i);
      return COL_STOP;
      //one stop by position
    }
  }
  log_debug("[geometry] finished walking stops\n");

  return COL_OUT;
}

DEFINE_HEPT_FUN(Map, lookup_pos, (Globals__position pos)) {
  float x = pos.x, y = pos.y;

  out->data.on_road = false;
  out->data.color = COL_OUT;
  out->data.max_speed = SPEED_MIN;
  out->data.tl_number = -1;
  out->data.tl_required = false;
  out->data.dir_x = -1.0;
  out->data.dir_y = 0.0;

  log_debug("[geometry] querying pos (%.2f, %.2f)\n", x, y);
  if (map == NULL)
    log_fatal("[geometry] map has not been initialized\n");

  int min_rd = -1;
  double min_d = 700.;
  for (int rid = 0; rid < map->road_sz; rid++) {
    road_t *rd = &map->road_arr[rid];
    double d = 0.;
    Globals__color col = COL_OUT;
    bool onRoad = false;
    float dir_X = 0.0;
    float dir_Y = 0.0;
    switch (rd->kind) {
    case RD_LINE_1:
      onRoad = isOnRoadLine1(rd, x, y, &col, &d, &dir_X, &dir_Y);
      break;
    case RD_LINE_2:
      onRoad = isOnRoadLine2(rd, x, y, &col, &d, &dir_X, &dir_Y);
      break;
    case RD_ARC:
      onRoad = isOnRoadArc(rd, x, y, &col, &d, &dir_X, &dir_Y);
      break;
    case RD_OTHER:
      break;
    }

    if (onRoad && (d < min_d)) {
      min_d = d;
      min_rd = rid;
      out->data.color = col;
      out->data.dir_x = dir_X;
      out->data.dir_y = dir_Y;
      out->data.max_speed = map->road_arr[min_rd].max_speed;
      /* Update color when a waypoint or stop. */
      col = getColorPoint(rid, x, y);
      if (colors_equal(&col, &COL_OUT))
        out->data.color = out->data.color;
      else if (colors_equal(&col, &COL_STOP)) {
        /* TODO: update red color */
        out->data.color = col;
      }
      else {
        /* TODO: update green color */
        out->data.color = col;
      }
    }
  }

  /* Compute the return type. */
  if (min_rd >= 0) {
    log_debug("[geometry] (%.2f, %.2f) is on road %d\n", x, y, min_rd);
    out->data.on_road = true;
    int tl = -1;
    out->data.tl_number =
      isOnTLight(x, y, min_rd, out->data.dir_x, out->data.dir_y);
    out->data.tl_required = isAfterStop(x, y, min_rd,
                                      out->data.dir_x, out->data.dir_y, &tl);
    if(out->data.tl_required) {
      if (tl != out->data.tl_number) {
        log_debug("Warning: on TL %ld != ", out->data.tl_number);
        log_debug("after TL %d!\n", tl);
      }
      out->data.tl_number = tl;
    }
  }

  /* Log the result. */
  log_debug("[geometry] { on_road = %d; color = (%d, %d, %d);"
            " dir = (%2.2f, %2.2f); tl = (%d, %d); }\n",
            out->data.on_road,
            out->data.color.red, out->data.color.green, out->data.color.blue,
            out->data.dir_x, out->data.dir_y,
            out->data.tl_number, out->data.tl_required);
}


void play_asset_wav(SDL_AudioDeviceID audio_device, asset_wav_t *wav) {
  if (!audio_device) {
    log_info("[sdl] no audio device\n");
    return;
  }

  if (SDL_QueueAudio(audio_device, wav->buffer, wav->size))
    log_fatal("[sdl] could not queue audio\n");
}

asset_wav_t collision, wrong_dir, exit_road, light_run, speed_excess;
SDL_AudioDeviceID audio_device = 0;

DEFINE_HEPT_FUN(Map, soundEffects, (Globals__event evt, Globals__status sta)) {
  if (sta == Globals__Preparing)
    return;
  if (evt.exitRoad) {
    play_asset_wav(audio_device, &exit_road);
    log_info("[audio] car left the road\n");
  } else if (evt.collisionEvent) {
    play_asset_wav(audio_device, &collision);
    log_info("[audio] car has collided with an obstacle\n");
  } else if (evt.dirEvent) {
    play_asset_wav(audio_device, &wrong_dir);
    log_info("[audio] car goes in the wrong direction\n");
  } else if (evt.lightRun) {
    play_asset_wav(audio_device, &light_run);
    log_info("[audio] car has run a red light\n");
  } else if (evt.speedExcess) {
    play_asset_wav(audio_device, &speed_excess);
    log_info("[audio] car is going too fast\n");
  }
}
