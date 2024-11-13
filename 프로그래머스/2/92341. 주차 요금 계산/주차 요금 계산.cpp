#include <string>
#include <vector>
#include <iostream>
#include <cmath>

// 하루 최대 23:59까지 존재(23 * 60 + 59)
#define MAX_TIME 1439

using namespace std;

int time_to_minute(string& time) {
    int hour = stoi(time.substr(0, 2));
    int minute = stoi(time.substr(3));
    
    return hour * 60 + minute;
}

vector<int> solution(vector<int> fees, vector<string> records) {
    vector<int> answer;
    
    int visited[10000] = {0};
    int time[10000] = {0}; // 만약 들어온 차가 있다면, 들어온 분 시간을 기록
    int total[10000] = {0}; // out되면 여기에 누적해주기 out의 숫자에서 time에 있는 값을 빼서 여기에 저장. 그리고 time은 0으로 초기화해주기
    
    for (auto r : records) {
        string t = r.substr(0, 5); // 시간
        int c_n = stoi(r.substr(6, 10)); // 차량 번호
        string status = r.substr(11); // 차량 출입 여부
        
        int minute = time_to_minute(t); // 분으로 변환
        
        if (status == "IN") { // 입차
            time[c_n] = minute;
            visited[c_n] = 1;
        } else { // 출차
            total[c_n] += (minute - time[c_n]);
            time[c_n] = 0;
            visited[c_n] = 0;
        }
    }
    
    // 나가지 않은 차들 처리해주기
    for (int i = 0; i < 10000; i++) {
        if (visited[i] == 1) {
            total[i] += MAX_TIME - time[i];
        }
    }
    
    for (int i = 0; i < 10000; i++) {
        if (0 < total[i]) {
            if ( total[i] <= fees[0] ) {
                answer.push_back(fees[1]);
            }
            else if(total[i] > fees[0]) {
                answer.push_back(fees[1] + ( ceil((total[i] - fees[0]) / (double)fees[2]) * fees[3] ));
            }
        }
    }
    
    return answer;
}