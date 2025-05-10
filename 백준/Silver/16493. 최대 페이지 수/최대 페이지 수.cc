#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int N, M;
    cin >> N >> M;
    
    vector<int> days(M);
    vector<int> pages(M);
    
    for (int i = 0; i < M; i++) cin >> days[i] >> pages[i];
    
    vector<int> dp(N + 1, 0);
    
    for (int i = 0; i < M; i++) {
        int day = days[i];
        int page = pages[i];
        
        for (int j = N; j >= day; j--) {
            dp[j] = max(dp[j], dp[j - day] + page);
        }
    }
    
    cout << dp[N] << endl;
    
    return 0;
}
