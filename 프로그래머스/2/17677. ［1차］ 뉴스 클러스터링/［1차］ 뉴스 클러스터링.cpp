#include <string>
#include <vector>
#include <iostream>
#include <unordered_map>

using namespace std;

int max(int a, int b) {
    if (a > b) return a;
    return b;
}

int min(int a, int b) {
    if (a > b) return b;
    return a;
}

unordered_map<string, int> beforeSetting(string& str) {
    unordered_map<string, int> info;
    
    for (int i = 0; i < str.size()-1; i++) {
        string tmp = "";
        for (int j = i; j <= i+1; j++) {
            if ('a' <= str[j] && str[j] <= 'z') tmp += str[j];
            else if ('A' <= str[j] && str[j] <= 'Z') tmp += tolower(str[j]);
        }

        if (tmp.size() == 2) {
            if (info.find(tmp) == info.end()) info.insert(make_pair(tmp, 1));
            else info[tmp]++;
        }
    }
    
    return info;
}

int unionVector(unordered_map<string, int> firstInfo, unordered_map<string, int> secondInfo) {
    unordered_map<string, int> newInfo = firstInfo;
    
    for (auto s : secondInfo) {
        if (newInfo.find(s.first) == newInfo.end()) newInfo.insert(make_pair(s.first, s.second));
        else newInfo[s.first] = max(s.second, newInfo[s.first]);
    }
    
    int cnt = 0;
    for (auto n : newInfo) {
        cnt += n.second;
    }
    
    return cnt;
}

int intersectVector(unordered_map<string, int> firstInfo, unordered_map<string, int> secondInfo) {
    unordered_map<string, int> newInfo;
    
    for (auto s : secondInfo)
        if (firstInfo.find(s.first) != firstInfo.end()) newInfo.insert(make_pair(s.first, min(s.second, firstInfo[s.first])));
    
    int cnt = 0;
    for (auto n : newInfo)
        cnt += n.second;
    
    return cnt;
}

int solution(string str1, string str2) {
    int answer = 1;
    
    unordered_map<string, int> first, second;
    
    // 전처리
    first = beforeSetting(str1);
    second = beforeSetting(str2);
    
    int SUM, SAME;
    SUM = unionVector(first, second);
    SAME = intersectVector(first ,second);
    
    double result;
    if (SUM == 0) result = 1.0;
    else result = (double) SAME / SUM;
    
    answer = (int) (result * 65536);
    
    return answer;
}