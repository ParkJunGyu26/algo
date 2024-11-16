#include <string>
#include <vector>
#include <queue>
#include <iostream>

using namespace std;

int visited[1000001] = {0};
int res[1000001] = {0};

void bfs(int now, int y, int n) {
    queue<int> q;
    q.push(now);
    visited[now] = 1;
    res[now] = 0;
    
    while (!q.empty()) {
        int num = q.front();
        q.pop();
        
        int one = num+n;
        int two = num*2;
        int three = num*3;
        
        if (one <= y && visited[one] == 0) {
            visited[one] = 1;
            res[one] = res[num]+1;
            q.push(one);
        } 
        if (two <= y && visited[two] == 0) {
            visited[two] = 1;
            res[two] = res[num]+1;
            q.push(two);
        }
        if (three <= y && visited[three] == 0) {
            visited[three] = 1;
            res[three] = res[num]+1;
            q.push(three);
        }
    }
}

int solution(int x, int y, int n) {
    
    bfs(x, y, n);
    
    if (visited[y] == 0) return -1;
    return res[y];
}