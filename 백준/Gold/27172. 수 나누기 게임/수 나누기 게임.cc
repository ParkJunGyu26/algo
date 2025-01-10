#include <iostream>
#include <vector>
#include <unordered_map>
#include <cmath>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;

    vector<int> num(n);
    unordered_map<int, int> um;

    for (int i = 0; i < n; i++) {
        cin >> num[i];
        um[num[i]] = 0;
    }

    for (int i = 0; i < n; i++) {
        int current = num[i];
        for (int j = 1; j <= (int)sqrt(current); j++) {
            if (current % j == 0) {
                // j가 약수인 경우
                if (um.find(j) != um.end()) {
                    um[j]++;
                    um[current]--;
                }
                // current / j가 다른 약수인 경우
                int pairDivisor = current / j;
                if (j != pairDivisor && um.find(pairDivisor) != um.end()) {
                    um[pairDivisor]++;
                    um[current]--;
                }
            }
        }
    }

    for (int i = 0; i < n; i++) {
        cout << um[num[i]] << " ";
    }

    return 0;
}
