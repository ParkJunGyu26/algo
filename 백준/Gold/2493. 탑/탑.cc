#include <iostream>
#include <stack>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	vector<int> info;
	vector<int> res;

	stack<int> st;

	cin >> n;
	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		info.push_back(tmp);
	}


	for (int i = 0; i < n; i++) {
		res.push_back(0);
	}

	for (int i = n-1; i >= 0; i--) {
		while(!st.empty() && info[st.top()-1] < info[i]) {
			res[st.top()-1] = i+1;
			st.pop();
		}
		st.push(i+1);
	}

	for (auto r : res)
		cout << r << " ";
}