#include <string>
#include <vector>
#include <cmath>

using namespace std;

int solution(vector<int> num_list) {
    int answer = 0;
    int tmp1 = 1, tmp2 = 0;
    for (auto num : num_list) {
        tmp1 *= num;
        tmp2 += num;
    }
    answer = tmp1 < pow(tmp2, 2) ? 1 : 0;
    return answer;
}