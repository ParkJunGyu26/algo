#include <string>
#include <tuple>
#include <unordered_map>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

// 좌표가 아니라 길이 중요한거임
// 역방향도 고려 -> (5, 4, U) == (5, 5, D)
int solution(string dirs) {
    vector<tuple<int, int, char>> answer;
    
    unordered_map<char, int> info;
    info.insert(make_pair('U', 3));
    info.insert(make_pair('D', 2));
    info.insert(make_pair('R', 0));
    info.insert(make_pair('L', 1));
    
    int x, y;
    x = y = 5;
    
    for (int i = 0; i < dirs.size(); i++) {
        int nx = x + dx[info[dirs[i]]];
        int ny = y + dy[info[dirs[i]]];
        
        tuple<int, int, char> target(nx, ny, dirs[i]); // 다음 위치
        
        tuple<int, int, char> up_to_bottom(nx, ny+1, 'D');
        tuple<int, int, char> bottom_to_up(nx, ny-1, 'U');
        tuple<int, int, char> left_to_right(nx+1, ny, 'R');
        tuple<int, int, char> right_to_left(nx-1, ny, 'L');
        
        if (-1 < nx && nx < 11 && -1 < ny && ny < 11) {
            
            if (dirs[i] == 'U') { // 밑에서 위로 올라옴
                if (find(answer.begin(), answer.end(), target) == answer.end() && 
                    find(answer.begin(), answer.end(), up_to_bottom) == answer.end())
                    answer.push_back(target);
            } else if (dirs[i] == 'D') { // 위에서 밑으로 내려감
                if (find(answer.begin(), answer.end(), target) == answer.end() && 
                    find(answer.begin(), answer.end(), bottom_to_up) == answer.end())
                    answer.push_back(target);
            } else if (dirs[i] == 'R') {
                if (find(answer.begin(), answer.end(), target) == answer.end() && 
                    find(answer.begin(), answer.end(), right_to_left) == answer.end())
                    answer.push_back(target);
            } else {
                if (find(answer.begin(), answer.end(), target) == answer.end() && 
                    find(answer.begin(), answer.end(), left_to_right) == answer.end())
                    answer.push_back(target);
            }
            
            x = nx;
            y = ny;
        }
    }
    
    return answer.size();
}