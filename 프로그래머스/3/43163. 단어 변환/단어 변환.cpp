#include <string>
#include <vector>
#include <iostream>

#define MAX_SIZE 51

using namespace std;

int answer;
int visited[MAX_SIZE] = {0};

bool check_alphabet(string begin, string word) {
    int cnt = 0;
    for (int i = 0; i < begin.size(); i++)
        if (begin[i] != word[i]) cnt++;
    
    return (cnt == 1);
}

void dfs(string& begin, string& target, vector<string>& words, int cnt) {
    if (begin == target) {
        answer = min(answer, cnt);
        return;
    }
    
    for (int i = 0; i < words.size(); i++) {
        if (check_alphabet(begin, words[i]) && visited[i] == 0) {
            cnt++;
            visited[i] = 1;
            dfs(words[i], target, words, cnt);
            cnt--;
            visited[i] = 0;
        }
    }
}

int solution(string begin, string target, vector<string> words) {
    int cnt = 0;
    answer = 100;
    dfs(begin, target, words, cnt);
    if (answer == 100) return 0;
    return answer;
}