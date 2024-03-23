#include <string>
#include <vector>
#include <map>

using namespace std;

int solution(vector<int> array) {
    int answer = 0;
    int m = 0;
    
    map<int, int> mp;
    
    for (int i = 0; i < array.size(); i++)
    {
        mp[array[i]]++;
    }
    
    for (auto it : mp)
    {
        if (it.second > m)
        {
            answer = it.first;
            m = it.second;
        }
        
        else if (it.second == m)
        {
            answer = -1;
        }
    }
    
    return answer;
}