#include <iostream>
#include <vector>
#include <deque>
#include <algorithm>

using namespace std;

// 접시 개수, 초밥 개수, 연속해서 먹는 수, 쿠폰 번호
int n, d, k, c;
int answer = 0;
int duplicate = 0;
vector<int> vec, info;
deque<int> dq; // 인덱스, 값

// 슬라이딩 윈도우 -> 덱에 슬라이딩 윈도우처럼 계속 넣되, 똑같은 숫자가 있을 때는 bool 로 체크해서 그 윈도우는 카운팅하지 않도록 하기 -> 중요한 건 모두 다 슬라이딩 윈도우로 만들어봐야됨
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> d >> k >> c;
	vec.resize(n);
	info.resize(d+1); // 초밥이 현재 덱에 몇 개 있는지

	for (int i = 0; i < n; i++) cin >> vec[i];

	for (int i = 0; i < k; i++) {
		dq.push_back(vec[i]);
		info[vec[i]]++;

		if (info[vec[i]] > 1) duplicate++;
	}

	if (duplicate == 0) answer += dq.size();
	if (info[c] == 0) answer++;

	for (int i = k; i < n+k-1; i++) {
		// cout << "i : " << i << "\n";
		int First = dq.front();
		dq.pop_front();
		if (info[First] >= 2) duplicate--;
		info[First]--;
		// cout << "First : " << First << ", duplicate : " << duplicate << "\n";

		int Last = vec[i%n];
		dq.push_back(Last);
		info[Last]++;
		if (info[Last] >= 2) duplicate++;
		// cout << "Last : " << Last << ", duplicate : " << duplicate << "\n";

		// cout << "-----\n";

		int cnt = (info[c] == 0) ? dq.size()+1 : dq.size();
		cnt -= duplicate;

		// cout << "cnt : " << cnt << "\n";

		answer = max(cnt, answer);
	}

	cout << answer;
}