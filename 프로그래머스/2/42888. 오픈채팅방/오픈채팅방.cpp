#include <string>
#include <vector>
#include <unordered_map>
#include <iostream>
#include <sstream>

using namespace std;

unordered_map<string, string> info;
string status, email, nickname;

vector<string> solution(vector<string> record) {
    vector<string> answer;
    
    for (auto r : record) {
        stringstream ss(r);
        ss >> status >> email >> nickname;
        if (status == "Enter") {
            if (info.find(email) == info.end()) info.insert(make_pair(email, nickname));
            else info[email] = nickname;
            answer.push_back(status + " " + email);
        } else if (status == "Leave") {
            answer.push_back(status + " " + email);
        } else if (status == "Change") {
            info[email] = nickname;
        }
    }
    
    vector<string> result;
    for (auto a : answer) {
        stringstream ss(a);
        ss >> status >> email;
        if (status == "Enter") {
            result.push_back(info[email] + "님이 들어왔습니다.");
        } else {
            result.push_back(info[email] + "님이 나갔습니다.");
        }
    }
    
    return result;
}