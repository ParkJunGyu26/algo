#include <string>
#include <vector>
#include <unordered_set>

#define MAX_SIZE 10000000

using namespace std;

bool sosu[MAX_SIZE];
unordered_set<int> answer;

void sosu_init() {
    sosu[0] = sosu[1] = 1;
    for (int i = 2; i < MAX_SIZE; i++) {
        if (sosu[i] == 0) {
            for (int j = i * 2; j < MAX_SIZE; j += i) {
                sosu[j] = 1;
            }
        }
    }
}

void dfs(int target_size, string &numbers, string &hubo, vector<bool> &visited) {
    if (hubo.size() == target_size) {
        int target_num = stoi(hubo);
        if (sosu[target_num] == 0) {
            answer.insert(target_num);
        }
        return;
    }
    
    for (int i = 0; i < numbers.size(); i++) {
        if (!visited[i]) {
            visited[i] = true;
            hubo += numbers[i];
            
            dfs(target_size, numbers, hubo, visited);
            
            visited[i] = false;
            hubo.pop_back();
        }
    }
}

int solution(string numbers) {
    answer.clear();
    sosu_init();
    
    int L = numbers.size();
    for (int i = 1; i <= L; i++) {
        string hubo = "";
        vector<bool> visited(L, false);
        dfs(i, numbers, hubo, visited);
    }
    return answer.size();
}