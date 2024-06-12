#define MAX_LINE 256
#define MAX_TOKENS 64

int line_to_tokens(char *line, char **tokens);
char **parse_connector(char **tokens);
int shift(char **tokens, int gap);
int parse_redirections(char **tokens, char *in_out[2], int *flag);
