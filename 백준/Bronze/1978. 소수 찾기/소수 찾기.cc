#include <iostream>
#include <vector>
using namespace std;

// 에라토스테네스의 체로 소수 판별을 위한 배열을 만드는 함수
vector<bool> sieve(int maximum) {
    vector<bool> isPrime(maximum + 1, true);
    isPrime[0] = isPrime[1] = false;
    
    for (int i = 2; i * i <= maximum; i++) {
        if (isPrime[i]) {
            for (int j = i * i; j <= maximum; j += i) {
                isPrime[j] = false;
            }
        }
    }
    return isPrime;
}

int main() {
    int N;
    cin >> N;
    
    // 입력값이 1000 이하이므로 1000까지의 소수 판별 배열 생성
    vector<bool> isPrime = sieve(1000);
    
    int count = 0;
    for (int i = 0; i < N; i++) {
        int num;
        cin >> num;
        if (isPrime[num]) {
            count++;
        }
    }
    
    cout << count << endl;
    return 0;
}