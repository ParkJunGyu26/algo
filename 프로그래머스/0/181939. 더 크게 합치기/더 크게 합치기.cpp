#include <string>
#include <vector>

using namespace std;

int solution(int a, int b) {
    int answer = 0;
    int tmp1 = stoi(to_string(a) + to_string(b));
    int tmp2  = stoi(to_string(b) + to_string(a));
    if (tmp1 > tmp2) {
        answer = tmp1;
    } else {
        answer = tmp2;
    }
    
    return answer;
}