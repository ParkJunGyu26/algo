#include <string>
#include <vector>
#include <stack>

using namespace std;

int solution(vector<int> order) {
    vector<int> answer;
    stack<int> s;
    int index = 0;
    for (int i = 0; i < order.size(); i++) {
        s.push(i+1);
        if ((i+1) == order[index]) {
            answer.push_back(i+1);
            s.pop();
            index++;
        }
        while (!s.empty() && s.top() == order[index]) {
            answer.push_back(s.top());
            s.pop();
            index++;
        }
    }
    return answer.size();
}