#include <iostream>
#include <vector>

using namespace std;

int n;
vector<int> vec;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> n;
    vec.resize(n);
    
    for (int i = 0; i < n; i++) {
        cin >> vec[i];
    }
    
    vector<int> stack;
    stack.push_back(vec[0]);
    for (int i = 1; i < n; i++) {
        if (stack.back() < vec[i]) stack.push_back(vec[i]);
        else {
            int left = 0;
            int right = stack.size()-1;
            
            int index = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                
                if (stack[mid] == vec[i]) {
                    index = mid;
                    break;
                } else if (stack[mid] < vec[i]) {
                    left = mid +1;
                } else {
                    right = mid-1;
                    index = mid;
                }
            }
            
            stack[index] = vec[i];
        }
    }
    
    cout << n - stack.size();
}