#include <vector>
#include <cmath>

using namespace std;

int solution(int n, vector<int> stations, int w) {
    int answer = 0;
    int position = 1;  // 현재 위치
    int index = 0;     // stations 배열의 인덱스
    
    while (position <= n) {
        // 현재 위치가 기존 기지국의 범위 안에 있는 경우
        if (index < stations.size() && position >= stations[index] - w) {
            position = stations[index] + w + 1;
            index++;
        }
        // 현재 위치가 기존 기지국의 범위를 벗어난 경우
        else {
            answer++;
            position += 2 * w + 1;
        }
    }
    
    return answer;
}