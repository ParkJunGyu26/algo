#include <string>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> sizes) {
    int max_width = 0;
    int max_height = 0;
    
    // 각 사이즈 쌍을 순회하면서 작은 값은 항상 한쪽에, 큰 값은 다른 쪽에 놓음
    for (auto& size : sizes) {
        int w = min(size[0], size[1]);
        int h = max(size[0], size[1]);
        
        // 최대값 갱신
        max_width = max(max_width, w);
        max_height = max(max_height, h);
    }
    
    // 최소 지갑 크기 계산
    return max_width * max_height;
}