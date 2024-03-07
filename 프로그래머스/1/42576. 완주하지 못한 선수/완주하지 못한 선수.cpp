#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
    unordered_map<string, int> map;
    
    for (auto player : participant)
    {
        if (map.find(player) == map.end())
            map.insert(make_pair(player, 1));
        else
            map[player]++;
    }
    
    for (auto player : completion)
        map[player]--;
    
    for (auto player : participant)
    {
        if (map[player] > 0)
        {
            answer = player;
            break;
        }
    }
    
    return answer;
}