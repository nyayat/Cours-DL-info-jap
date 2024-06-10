#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>

// exo 2
int nbr_words (const char *s) {
    int nbr = 0;
    for (int i = 0; s[i] != '\0'; i++) {
		if (!isspace(s[i]) &&
			(i == 0 || isspace(s[i - 1]))) {
			nbr++;
		}
    }
    return nbr;
}

// exo 3
int word_len (const char *w) {
    int len = 0;
    for (len  = 0; !isspace(w[len]) && w[len] != '\0'; len++);
    return len;
}

char *extract_word (const char *w, int *pl) {
    int wl = word_len (w);
    *pl = wl;
    char *s = malloc (1 + wl);
    assert (s != NULL);
    strncpy (s, w, wl);
    s[wl] = '\0';
    return s;
}
    
typedef struct {
    int nbr;
    char **words;
} w_index;

// exo 4

void free_index(w_index *pi) {
	if (pi != NULL) {
		if (pi->words != NULL) {
			for (int i=0;i<pi->nbr;i++) 
				free(pi->words[i]);
		}
		free(pi);
	}
}

// exo 5
int size_words (w_index *pi) {
    int len = 0;
    for (int i = 0; i < pi -> nbr; i++) {
		len += strlen (pi -> words[i]);
    }
    return len;
}

char *concat_words (w_index *pi) {
    int z = pi -> nbr ? 0 : 1;
    char *s = malloc (z + pi -> nbr + size_words (pi));
    assert (s != NULL);
    int jw = 0;
    for (int i = 0; i < pi -> nbr; i++) {
		for (int j = 0; pi -> words[i][j] != '\0'; j++) {
			s[jw++] = pi -> words[i][j];
		}
		if (i < pi -> nbr - 1) {
			s[jw++] = ' ';
		}
    }
    s[jw] = '\0';
    return s;
}

w_index *cons_index (const char *s) {
    w_index *pi = malloc (sizeof (w_index));
    assert (pi != NULL);
    int nbr = nbr_words(s);
    pi -> nbr = nbr;
    pi -> words = malloc (nbr * sizeof (char *));
    int i = 0;
    while (*s != '\0') {
		if (isspace (*s)) {
			s++;
			continue;
			}
		int wl;
		pi -> words [i] = extract_word (s, &wl);
		i++;
		s += wl;
    }
    return pi;
}

int main () {
    w_index *pi = cons_index ("abc de f");
    for (int i = 0; i < pi -> nbr; i++) {
		printf ("%d : %s\n", i, pi -> words[i]);
    }
    char *s = concat_words (pi);
    printf ("%s\n", s);
}
   
    
    



