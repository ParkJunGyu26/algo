#include <string>
#include <vector>
#include <unordered_map>
#include <iostream>

using namespace std;

vector<int> solution(string msg) {
    vector<int> answer;
    unordered_map<char, int> alphabet;
    unordered_map<string, int> word;
    
    for (int i = 0; i < 26; i++)
        alphabet.insert(make_pair('A'+i, i+1));
    
    int number = 27;
    int i = 0;
    while (i < msg.size()) {
        string target = "";
        target += msg[i];
        int index = i;
        
        while (index < msg.size()) {
            index++;
            target += msg[index];
            
            if (word.find(target) == word.end()) {
                word.insert(make_pair(target, number));
                number++;
                break;
            }
        }
        
        if (target.size() > 1) target.pop_back();
        if (index - i > 1) { // 단어
            answer.push_back(word[target]);
            i = index-1;
        } else { // 알파벳
            char tmp = target[0];
            answer.push_back(tmp - 'A' +1);
        }
        
        i++;
    }
    
    return answer;
}