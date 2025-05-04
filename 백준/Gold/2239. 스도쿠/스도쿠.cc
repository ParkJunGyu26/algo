#include <iostream>
#include <string>
#include <vector>
#include <unordered_set>

using namespace std;

bool check = false;
vector<vector<pair<int, int>>> vec; // 좌표숫자 + 구역번호
vector<unordered_set<int>> location(9); // 0구역 ~ 8구역
vector<unordered_set<int>> garo(9); // 0행 ~ 8행
vector<unordered_set<int>> sero(9); // 0열 ~ 8열

void Print() {
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) cout << vec[i][j].first;
        cout << "\n";
    }
}

bool inRange(int y, int x, int number) {
    int L = vec[y][x].second;
    return (
        location[L].find(number) == location[L].end() &&
        garo[y].find(number) == garo[y].end() &&
        sero[x].find(number) == sero[x].end()
    );
}

void insertRange(int y, int x, int number) {
    vec[y][x].first = number;
    int L = vec[y][x].second;
    location[L].insert(number);
    garo[y].insert(number);
    sero[x].insert(number);
}

void deleteRange(int y, int x, int number) {
    vec[y][x].first = 0;
    int L = vec[y][x].second;
    location[L].erase(number);
    garo[y].erase(number);
    sero[x].erase(number);
}

// 수정된 백트래킹 함수
void backtracking(int pos) {
    if (check) return;
    
    // 모든 셀을 다 채웠으면 종료
    if (pos == 81) {
        check = true;
        return;
    }
    
    // 현재 위치의 행과 열 계산
    int y = pos / 9;
    int x = pos % 9;
    
    // 이미 숫자가 채워져 있으면 다음 셀로 이동
    if (vec[y][x].first != 0) {
        backtracking(pos + 1);
        return;
    }
    
    // 빈 셀이면 1부터 9까지 가능한 숫자 시도
    for (int num = 1; num <= 9; num++) {
        if (inRange(y, x, num)) {
            insertRange(y, x, num);
            backtracking(pos + 1);
            
            // 해결책을 찾았으면 더 이상 진행하지 않음
            if (check) return;
            
            // 해결책을 찾지 못했으면 숫자를 지우고 다음 숫자 시도
            deleteRange(y, x, num);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    vec.resize(9, vector<pair<int, int>>(9));
    for (int i = 0; i < 9; i++) {
        string tmp;
        cin >> tmp;

        for (int j = 0; j < 9; j++) {
            vec[i][j].first = tmp[j] - '0';
            int number = vec[i][j].first;
            
            // 구역 번호 설정 및 초기 값 삽입
            if (i < 3) {
                if (j < 3) vec[i][j].second = 0;
                else if (j < 6) vec[i][j].second = 1;
                else vec[i][j].second = 2;
            } else if (i < 6) {
                if (j < 3) vec[i][j].second = 3;
                else if (j < 6) vec[i][j].second = 4;
                else vec[i][j].second = 5;
            } else {
                if (j < 3) vec[i][j].second = 6;
                else if (j < 6) vec[i][j].second = 7;
                else vec[i][j].second = 8;
            }
            
            // 0이 아닌 숫자만 집합에 추가
            if (number != 0) {
                int L = vec[i][j].second;
                location[L].insert(number);
                garo[i].insert(number);
                sero[j].insert(number);
            }
        }
    }

    // 백트래킹 시작 (0번 위치부터)
    backtracking(0);
    
    // 결과 출력
    if (check) Print();
    
    return 0;
}
