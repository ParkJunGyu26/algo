#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

using namespace std;

struct Info {
	int order;
	int age;
	string name;
};

bool cmp(Info a, Info b) {
	if (a.age == b.age) {
		return a.order < b.order;
	}
	return a.age < b.age;
}

int main() {
	int n;
	cin >> n;

	int age;
	string name;
	vector<Info> vec;
	for (int i = 0; i < n; i++) {
		cin >> age >> name;
		vec.push_back({i, age, name});
	}

	sort(vec.begin(), vec.end(), cmp);
	for (auto v : vec)
		cout << v.age << " " << v.name << "\n";

}