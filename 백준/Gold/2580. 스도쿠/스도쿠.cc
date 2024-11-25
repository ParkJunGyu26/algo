#include <iostream>
#include <vector>
using namespace std;

const int N = 9;
int board[N][N];
vector<pair<int, int>> blanks;

// 특정 위치 (row, col)에 num이 들어갈 수 있는지 확인
bool isValid(int row, int col, int num) {
    // 행과 열 확인
    for (int i = 0; i < N; ++i) {
        if (board[row][i] == num || board[i][col] == num) {
            return false;
        }
    }
    // 3x3 박스 확인
    int boxRowStart = (row / 3) * 3;
    int boxColStart = (col / 3) * 3;
    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j) {
            if (board[boxRowStart + i][boxColStart + j] == num) {
                return false;
            }
        }
    }
    return true;
}

// 백트래킹 함수
bool solve(int idx) {
    if (idx == blanks.size()) {
        return true; // 모든 빈칸을 채웠으면 종료
    }

    int row = blanks[idx].first;
    int col = blanks[idx].second;

    for (int num = 1; num <= 9; ++num) {
        if (isValid(row, col, num)) {
            board[row][col] = num; // 숫자를 채운다
            if (solve(idx + 1)) {  // 다음 빈칸을 시도
                return true;
            }
            board[row][col] = 0; // 실패하면 되돌린다
        }
    }
    return false; // 가능한 숫자가 없으면 실패
}

int main() {
    // 입력받기
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            cin >> board[i][j];
            if (board[i][j] == 0) {
                blanks.emplace_back(i, j);
            }
        }
    }

    // 스도쿠 풀이
    solve(0);

    // 결과 출력
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            cout << board[i][j] << " ";
        }
        cout << "\n";
    }

    return 0;
}
