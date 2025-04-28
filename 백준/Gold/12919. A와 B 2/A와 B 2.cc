#include <iostream>
#include <string>
#include <queue>
#include <algorithm>

using namespace std;

string s, t;

void bfs() {
    queue<string> q;
    q.push(t);

    while (!q.empty()) {
        string target = q.front();
        q.pop();

        if (target == s) {
            cout << 1;
            return;
        }

        if (target.size() <= s.size()) continue;

		string nTarget = target.substr(0, target.size()-1);
    
        if (target.back() == 'A') {
			q.push(nTarget);
		}
        
		nTarget = target;
		nTarget.erase(nTarget.begin());
		reverse(nTarget.begin(), nTarget.end());

        if (target[0] == 'B') {
            q.push(nTarget);
        }

    }

    cout << 0;
}

int main() {
    ios_base::sync_with_stdio(false);
    cout.tie(NULL);
    cin.tie(NULL);

    cin >> s >> t;
    bfs();

}