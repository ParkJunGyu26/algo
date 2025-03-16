#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

struct File{
string head;
string number;
string tail;
int index;
};

bool cmp(File a, File b) {
    string a_h = "";
    string b_h = "";
    
    for (int i = 0; i < a.head.size(); i++) a_h += tolower(a.head[i]);
    for (int i = 0; i < b.head.size(); i++) b_h += tolower(b.head[i]);
    
    if (a_h != b_h) return a_h < b_h;
    
    int a_n = stoi(a.number);
    int b_n = stoi(b.number);
    
    if (a_n != b_n) return a_n < b_n;
    
    return a.index < b.index;
}

vector<string> solution(vector<string> files) {
    vector<string> answer;
    vector<File> vec;
    
    for (int i = 0; i < files.size(); i++) {
        string head = "";
        string number = "";
        string tail = "";
        for (int j = 0; j < files[i].size(); j++) {
            if ('0' <= files[i][j] && files[i][j] <= '9') {
                if (tail.size() == 0) number += files[i][j];
                else tail += files[i][j];
            } else {
                if (number.size() == 0) head += files[i][j];
                else tail += files[i][j];
            }
        }
        vec.push_back({head, number, tail, i});
    }
    
    sort(vec.begin(), vec.end(), cmp);
    
    for (int i = 0; i < vec.size(); i++) {
        cout << "head : " << vec[i].head << "\n";
        cout << "number : " << vec[i].number << "\n";
        cout << "tail : " << vec[i].tail << "\n----\n";
        answer.push_back(vec[i].head + vec[i].number + vec[i].tail);
    }
    
    return answer;
}