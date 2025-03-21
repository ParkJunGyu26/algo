#include <iostream>
#include <vector>
#include <stack>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int n;
    cin >> n;
    
    vector<int> h(n);
    for (int i = 0; i < n; i++) {
        cin >> h[i];
    }
    
    stack<pair<int, int>> st; // {height, count}
    long long answer = 0;
    
    for (int i = 0; i < n; i++) {
        int cnt = 1;
        while (!st.empty() && st.top().first <= h[i]) {
            answer += st.top().second;
            if (st.top().first == h[i]) {
                cnt += st.top().second;
            }
            st.pop();
        }
        if (!st.empty()) {
            answer++;
        }
        st.push({h[i], cnt});
    }
    
    cout << answer;
    return 0;
}