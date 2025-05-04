#include <iostream>
#include <vector>
#include <string>

using namespace std;

// 스도쿠 보드
vector<vector<int>> board(9, vector<int>(9));

// 각 행, 열, 3x3 박스에 숫자 사용 여부를 저장
bool row_used[9][10] = {false};
bool col_used[9][10] = {false};
bool box_used[9][10] = {false};

// 빈 칸 위치 저장
vector<pair<int, int>> empty_cells;

// 숫자가 해당 위치에 들어갈 수 있는지 확인
bool isValid(int row, int col, int num) {
    int box_idx = (row / 3) * 3 + (col / 3);
    return !row_used[row][num] && !col_used[col][num] && !box_used[box_idx][num];
}

// 백트래킹 함수
bool solve(int idx) {
    // 모든 빈 칸을 채웠으면 성공
    if (idx == empty_cells.size()) {
        return true;
    }
    
    int row = empty_cells[idx].first;
    int col = empty_cells[idx].second;
    int box_idx = (row / 3) * 3 + (col / 3);
    
    // 1부터 9까지 시도
    for (int num = 1; num <= 9; num++) {
        if (isValid(row, col, num)) {
            // 숫자 배치
            board[row][col] = num;
            row_used[row][num] = true;
            col_used[col][num] = true;
            box_used[box_idx][num] = true;
            
            // 다음 빈 칸으로 진행
            if (solve(idx + 1)) {
                return true;
            }
            
            // 백트래킹: 선택 취소
            board[row][col] = 0;
            row_used[row][num] = false;
            col_used[col][num] = false;
            box_used[box_idx][num] = false;
        }
    }
    
    // 현재 빈 칸에 가능한 숫자가 없음
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    // 스도쿠 보드 입력
    for (int i = 0; i < 9; i++) {
        string line;
        cin >> line;
        for (int j = 0; j < 9; j++) {
            board[i][j] = line[j] - '0';
            
            // 이미 채워진 숫자 표시
            if (board[i][j] != 0) {
                int num = board[i][j];
                row_used[i][num] = true;
                col_used[j][num] = true;
                box_used[(i / 3) * 3 + (j / 3)][num] = true;
            } else {
                // 빈 칸 위치 저장
                empty_cells.push_back({i, j});
            }
        }
    }
    
    // 스도쿠 풀기
    solve(0);
    
    // 결과 출력
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            cout << board[i][j];
        }
        cout << "\n";
    }
    
    return 0;
}
