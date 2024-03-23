#include <string>
#include <vector>

using namespace std;

int solution(int a, int d, vector<bool> included) {
    int answer = 0;
    int i = 0;
    for (bool chk : included) {
        if (chk == true) {
            answer += a + (d*i);
        }
        i++;
    }
    return answer;
}