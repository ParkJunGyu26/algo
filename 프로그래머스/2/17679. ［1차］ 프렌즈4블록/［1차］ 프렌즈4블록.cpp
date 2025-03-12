#include <string>
#include <vector>
#include <iostream>

using namespace std;

int dx[4] = {1, 1, 0, 0};
int dy[4] = {0, 1, 1, 0};

// O(N^3)
void move(int m, int n, vector<string> &board) {
    // 각 열별로 한 번에 처리
    for (int j = 0; j < n; j++) {
        // 아래에서부터 위로 이동
        int writePos = m - 1;  // 쓸 위치
        
        for (int i = m - 1; i >= 0; i--) {
            if (board[i][j] != '-') {  // 블록이 있다면
                if (i != writePos) {
                    board[writePos][j] = board[i][j];
                    board[i][j] = '-';
                }
                writePos--;
            }
        }
        
        // 남은 윗부분을 모두 '-'로 채움
        while (writePos >= 0) {
            board[writePos][j] = '-';
            writePos--;
        }
    }
}

int solution(int m, int n, vector<string> board) {
    int answer = 0;
    
    while (true) {
        vector<vector<bool>> willRemove(m, vector<bool>(n, false));
        int cnt = 0;
        
        // 지울 블록 체크
        for (int i = 0; i < m-1; i++) {
            for (int j = 0; j < n-1; j++) {
                if (board[i][j] == '-') continue;
                
                if (board[i][j] == board[i][j+1] && 
                    board[i][j] == board[i+1][j] && 
                    board[i][j] == board[i+1][j+1]) {
                    willRemove[i][j] = willRemove[i][j+1] = 
                    willRemove[i+1][j] = willRemove[i+1][j+1] = true;
                }
            }
        }
        
        // 블록 지우기
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (willRemove[i][j]) {
                    board[i][j] = '-';
                    cnt++;
                }
            }
        }
        
        if (cnt == 0) break;
        
        move(m, n, board);
        answer += cnt;
    }
    
    return answer;
}