#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
#include<stdlib.h>


void binary_search(int *arr, int right, int k) //二分查找法
{
	int left = 0;
	
	while (left <= right)
	{
		int mid = (left + right) / 2;
		if (arr[mid] > k)
		{
			right = mid - 1;
		}
		else if (mid[arr] < k)
		{
			left = mid + 1;
		}
		else
		{
			printf("找到了，下标为：%d\n", mid);
			break;
		}
	}
	if (left>right)
		printf("找不到！\n");
}
int main()
{
	int n;
	int a[10] = { 1, 2, 3, 4, 5, 6, 7, 8, 9,10 };
	int ret = sizeof(a) / sizeof(a[0]) - 1;
	printf("请输入要查找的数：\n");
	scanf("%d", &n);
	binary_search(a,ret,n);
	system("pause");
	return 0;
}