#define MAX_JOBS 64

#ifdef MAIN
pid_t pid_jobs[MAX_JOBS];
int nb_jobs;
#else
extern pid_t pid_jobs[];
extern int nb_jobs;
#endif

void execute(char **argv, char *ref_in, char *ref_out, int flag);
int exec_external(char **argv, char *in_out[2], int flag, int bg);
int is_builtin(char *cmd);
int exec_builtin(int argc, char **argv, char *in_out[2], int flag);
void examine_bg ();
