#ifndef MAP_H
#define MAP_H

#include <assert.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

#include <SDL.h>

#include "pervasives.h"
#include "hept_ffi.h"
#include "globals_types.h"

#define MAX_ROAD_COUNT  50               /* maximum number of roads */
#define MAX_WAYP_COUNT  50               /* maximum number of waypoints */
#define MAX_TL_COUNT    Globals__trafnum /* maximum number of traffic lights */
#define MAX_STOP_COUNT  50               /* maximum number of stops */
#define MAX_OBST_COUNT  Globals__obstnum /* maximum number of obstacles */
#define MAX_ITI_COUNT   Globals__itinum  /* maximum number of itinerary steps */

/*
 * =========================================================================
 * Reference system
 * =========================================================================
 */

/** Size of the Carthesian space (in cm) */
#define MIN_X 0
#define MIN_Y 0
#define MAX_X 600
#define MAX_Y 300

/** Precision of comparison between points */
#define EPS 0.001

/** Type of points used, in a Carthesian space */
/** see @code{positionTy} in kcg_types.h */

float toradian(float);

/*
 * =========================================================================
 * Car size
 * =========================================================================
 */

/**
 * Car plan with o-point is the position of camera
 *
 * +---------------+
 * |  SC      |SB  |
 * |----------o----| SA
 * |          |SD  |
 * +---------------+
 *
 * Dimensions defined as constants in kcg_const.h
 * #define SA (kcg_lit_float64(3.0))
 * #define SB (kcg_lit_float64(3.0))
 * #define SD (kcg_lit_float64(3.0))
 * #define SC (kcg_lit_float64(7.0))
 */
#define CAR_WIDTH       (SB+SD)
#define CAR_HALF_WIDTH   SB

/*
 * =========================================================================
 * Colors
 * =========================================================================
 */

/** Color type @code{colorTy} defined in @see{kcg_types.h} */

/** True colors used
 *  defined in @see{kcg_const.h} as constants of SCADE
 *
 * extern const colorTy AMBER;
 * extern const colorTy GRAY;
 * extern const colorTy CYAN;
 * extern const colorTy MAGENTA;
 * extern const colorTy YELLOW;
 * extern const colorTy BLUE;
 * extern const colorTy GREEN;
 * extern const colorTy RED;
 */

/** Symbolic colors used for road, points, etc */
#define COL_ROADLINE   Globals__blue
#define COL_ROADLEFT   Globals__cyan
#define COL_ROADRIGHT  Globals__magenta
#define COL_WAYPOINT   Globals__green
#define COL_STOP       Globals__red
#define COL_OUT        Globals__gray

/** String colors used to print */
#define STR_ROADLINE   "blue"
#define STR_ROADLEFT   "cyan"
#define STR_ROADRIGHT  "magenta"
#define STR_WAYPOINT   "yellow"
#define STR_STOP       "red"
#define STR_TLIGHT     "orange"
#define STR_OUT        "gray"

/*
 * =========================================================================
 * Road segments and points
 * =========================================================================
 */

/** Kind of road segments */
typedef enum {
  RD_LINE_1 = 0,                /* linear road, one direction */
  RD_LINE_2,                    /* linear road, two directions */
  RD_ARC,                       /* arc road, one direction */
  RD_OTHER                      /* INTERNAL USE ONLY */
} road_kind_t;

/** Dimensions used for road (in cm) */
#define RD_SIZE_LINE        1.0
#define RD_SIZE_LINE2       2.0
#define RD_SIZE_HALF_WIDTH  10.0 /* 10.0 in simulator version */
#define RD_SIZE_WAYPOINT    1.0  /* the size of the half of the ruban */
#define RD_SIZE_STOP        1.0

/** Dimensions for traffic lights (in cm) */
#define TL_NUMBER   5
#define TL_VIEW     50.0
#define TL_COSDIR   0.5
#define STP_AFTER   3.0

/** Speeds */
#define SPEED_MIN 20
#define SPEED_MAX 40

typedef Globals__phase phase_t;

/** Road segment */
typedef struct {
  road_kind_t  kind;           /* kind to select informations */

  Globals__color color;          /* color used on the middle line */
  float          dir_x, dir_y;   /* direction vector/sense for line/arc (x) */
  int            max_speed;      /* maximal speed */

  union {
    struct {                    /* parameters for a linear road */
      Globals__position startp; /* on the middle line */
      Globals__position endp;   /* on the middle line */
    } line;
    struct {                         /* parameters for a arc road */
      Globals__position center;      /* the circle center */
      float             radius;      /* the circle radius (in cm) */
      float             start_angle; /* start and stop angles */
      float             end_angle;
    } arc;
  } u;
} road_t;

/** Waypoint used to consult the map */
typedef struct {
  int                   road;     /* road identifier */
  Globals__position     position; /* on the middle line of the road */
} waypoint_t;

/** Stop point used to signal a traffic light */
typedef struct {
  int                   road;     /* road identifier */
  int                   sema;     /* traffic light identifier */
  Globals__position     position; /* on the middle line of a road */
} stop_t;

/** Traffic lights: added road reference to type
 * @code{paramTLTy_City} defined in @see{kcg_tpes.h} */
typedef struct {
  Globals__param_tlight tl;
  int                   road;   /* road identifier that the tlight controls */
} tlight_t;

typedef Globals__param_obst obst_t;

typedef Globals__itielt iti_t;

typedef Globals__position position_t;

/*
 * =========================================================================
 * Maps
 * =========================================================================
 */

/** One map contains roads, waypoints, traffic lights and stop points */
typedef struct {
  char                 name[255];     /* Name of the map */
  char                 graphics[255]; /* Path to graphics file (bmp) */
  char                 guide[255];    /* Path to guide file (bmp) */
  phase_t              init_phase;    /* Initial phase of the robot */
  road_t               *road_arr;     /* Roads */
  int                  tlight_sz;     /* Road count */
  waypoint_t           *wayp_arr;     /* Waypoints */
  int                  road_sz;       /* Waypoint count */
  tlight_t             *tlight_arr;   /* Traffic lights */
  int                  wayp_sz;       /* Traffic light count */
  stop_t               *stop_arr;     /* Stops */
  int                  stop_sz;       /* Stop count */
  obst_t               *obst_arr;     /* Obstacles */
  int                  obst_sz;       /* Obstacle count */
  iti_t                *iti_arr;      /* Itinerary */
  int                  iti_sz;        /* Itinerary step count */
} map_t;

extern map_t *map;

void map_load(const char *);  /* parse and load global map file */
void map_destroy();           /* free the map loaded via load_map() */

/*
 * =========================================================================
 * Functions exported to the Heptagon side
 * =========================================================================
 */

typedef struct asset_wav {
  SDL_AudioSpec spec;
  Uint8 *buffer;
  Uint32 size;
} asset_wav_t;

extern asset_wav_t collision, wrong_dir, exit_road, light_run, speed_excess;
extern SDL_AudioDeviceID audio_device;

DECLARE_HEPT_FUN_NULLARY(Map,
                         read_obstacles,
                         Globals__param_obsts obst);
DECLARE_HEPT_FUN_NULLARY(Map,
                         read_traffic_lights,
                         Globals__param_tlights tlights);
DECLARE_HEPT_FUN_NULLARY(Map,
                         read_itinerary,
                         Globals__itielts iti);
DECLARE_HEPT_FUN(Map,
                 lookup_pos,
                 (Globals__position),
                 Globals__map_data data);
DECLARE_HEPT_FUN(Map,
                 soundEffects,
                 (Globals__event, Globals__status),);

#endif  /* MAP_H */
