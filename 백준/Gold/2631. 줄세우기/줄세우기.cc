#include <vector>
#include <stdio.h>
#include <algorithm>

using namespace std;

int n;
int vec[201];

int main() {
    
	scanf("%d", &n);
    
    for (int i = 0; i < n; i++) scanf("%d", &vec[i]);
    
    vector<int> stack;
    stack.push_back(vec[0]);
    for (int i = 1; i < n; i++) {
        if (stack.back() < vec[i]) stack.push_back(vec[i]);
        else stack[lower_bound(stack.begin(), stack.end(), vec[i]) - stack.begin()] = vec[i];
    }
    
	printf("%d", (n - stack.size()));
}