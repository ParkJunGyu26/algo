#include <string>
#include <iostream>
using namespace std;

// dp..?
int solution(int n)
{
    int change = n;
    int answer = 0;
    while (change > 0) {
        if (change % 2 != 0) {
            answer++;
            change--;
        }
        change /= 2;
    }
    return answer;
}