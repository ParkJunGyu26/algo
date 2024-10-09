#include <string>
#include <vector>

using namespace std;

int max(int a, int b) {
    if (a > b) return a;
    return b;
}

int min(int a, int b) {
    if (a > b) return b;
    return a;
}

int gcd(int a, int b) {
    int MAX = max(a, b);
    int MIN = min(a, b);
    
    int tmp;
    while (true) {
        if (MAX % MIN == 0) return MIN;
        
        tmp = MAX % MIN;
        MAX = MIN;
        MIN = tmp;
    }
}

int lcm(int a, int b) {
    return a * b / gcd(a, b);
}

int solution(vector<int> arr) {
    int answer = arr.back();
    arr.pop_back();
    
    while (!arr.empty()) {
        int target = arr.back();
        arr.pop_back();
        answer = lcm(answer, target);
    }
    
    return answer;
}