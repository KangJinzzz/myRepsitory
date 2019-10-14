#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
//一
#if 0
void Find(int (*a)[4], int Col, int Row, int k)
{
	int col = 0;
	int row =Row-1;
	while (col<Col && row>=0)
	{
		if (a[col][row] == k)
		{
			printf("yes\n");
			return;
		}
		if (a[col][row] > k)
		{
			row -= 1;
		}
		if (a[col][row] < k)
		{
			col += 1;
		}
	}
	printf("no\n");
	return 0;
}

int main()
{
	int arr[4][4] = { 1, 2, 8, 9, 2, 4, 9, 12, 4, 7, 10, 13, 6, 8, 11, 15 };
	Find(arr,4,4,9);
	return 0;
}
#endif

//二
#if 0

char * Replace(char *p)
{
	int count = 0;
	char *q = p;
	char *end = NULL;
	char *end_new = NULL;
	assert(p != NULL);
	while (*p != '\0')
	{
		if (*p == ' ')
		{
			count++;
		}
		p++;
	}
	end = p;
	end_new = p + 2 * count;
	while (end != end_new)
	{
		if (*end == ' ')
		{
			*end_new-- = '0';
			*end_new-- = '2';
			*end_new-- = '%';
			end--;
		}
		else
		{
			*end_new-- = *end--;
		}

	}
	return q;
}


int main()
{
	char arr[20] = "abc defgx yz";
	Replace(arr);
	printf("%s\n", arr);
	return 0;
}

#endif

//三
#if 0

int Fibonacci(int n)
{
	int i = 0;
	int a = 1;
	int b = 1;
	int sum = 0;
	if (n >= 3)
	{
		for (i = 0; i < n - 2; i++)
		{
			sum = a + b;
			a = b;
			b = sum;
		}
		return sum;
	}
	else
		return 1;
}

int Fibonacci2(int n)
{
	if (n > 2)
	{
		return Fibonacci2(n - 1) + Fibonacci2(n - 2);
	}
	else
		return 1;
}

int main()
{

	int n = 0;
	scanf("%d", &n);
	printf("%d\n", Fibonacci2(n));
	return 0;
}

#endif

//四
#if 0
int Count(unsigned int x)
{
	int count = 0;
	while (x)
	{
		if ((x % 2) & 1 == 1)
		{
			count++;
		}
		x /= 2;
	}
	return count;
}

int main()
{
	unsigned x = 0;
	scanf("%d", &x);
	printf("%d\n", Count(x));
	return 0;
}

#endif
//七  前k个最小的数
#if 0
//划分
int PartNum(int *arr, int left,int right)
{
	int tmp = 0;
	tmp = arr[left];
	while (left < right)
	{
		while (left<right && arr[right]>tmp)
		{
			right--;
		}
		
		if (left >= right)
		{
			arr[left] = tmp;
			break;
		}
		if (arr[right]<tmp)
		{
			arr[left] = arr[right];
		}


		while (left<right && arr[left]<tmp)
		{
			left++;
		}

		if (left >= right)
		{
			arr[left] = tmp;
			break;
		}
		if (arr[left]>tmp)
		{
			arr[right] = arr[left];
		}
	}
	arr[left] = tmp;
	return left;

}

void FinMin(int *arr, int len, int k)
{
	int par = 0;
	int left = 0;
	int right = len - 1;
	par = PartNum(arr, left, right);
	while (par != k - 1)
	{
		if (par > k - 1)
		{
			right = par - 1;
			par = PartNum(arr, left, right);
		}
		else if (par < k - 1)
		{
			left = par + 1;
			par = PartNum(arr, left, right);
		}
	}
	for (int i = 0; i < k; i++)
	{
		printf("%d ", arr[i]);
	}
	printf("\n");
}

int main()
{
	int arr[10] = { 9, 8, 7, 4, 15, 6, 1, 2, 13, 0 };
	int len = sizeof(arr) / sizeof(arr[0]);
	FinMin(arr, len, 5);
	
	return 0;
}
#endif

//八
#if 0
int Partion(int *arr, int left, int right)
{
	int tmp = arr[left];
	while (left < right)
	{ 
		while (left<right && arr[right]>tmp)
		{
			right--;
		}
		if (arr[right] < tmp)
		{
			arr[left] = arr[right];
		}
		else
		{
			arr[left] = tmp;
			break;
		}

		while (left<right && arr[left]<tmp)
		{
			left++;
		}
		if (arr[left] > tmp)
		{
			arr[right] = arr[left];
		}
		else
		{
			arr[left] = tmp;
			break;
		}
	}
	arr[left] = tmp;

	return left;
}
int FindHalfNum(int *arr, int len)
{
	int count = 0;
	int mid = len / 2;
	int left = 0;
	int right = len - 1;
	int par = Partion(arr, left, right);
	while (mid!=par)
	{
		if (mid < par)
		{
			right = par - 1;
			par = Partion(arr, left, right);
		}
		if (mid>par)
		{
			left = par + 1;
			par = Partion(arr, left, right);
		}
	}
	for (int i = 0; i < len; i++)
	{
		if (arr[mid] == arr[i])
		{
			count++;
		}
	}
	if (count>mid)
	{
		return arr[mid];
	}
	return 0;

}

int main()
{
	int arr[] = { 2, 5, 3, 2, 2, 2, 5, 4, 2 };
	int len = sizeof(arr) / sizeof(arr[0]);
	int ret = FindHalfNum(arr, len);
	printf("%d\n", ret);
	return 0;
}
#endif
//九
#if 0
// 时间复杂度为O(n^2)
int FindMax(int *arr, int len)
{
	int i, j, max = arr[0];
	int sum = 0;
	for (i = 0; i < len ; i++)
	{
		sum = 0;
		for (j = i; j < len ; j++)
		{
			sum += arr[j];
			if (sum>max)
			{
				max = sum;
			}
		}
	}
	return max;
}

// 时间复杂度为O(n)
int FindMax2(int *arr, int len)
{
	int i = 0;
	int sum = 0;
	int max = arr[0];
	for (i = 0; i < len; i++)
	{
		if (sum+arr[i] < arr[i])
		{
			sum = arr[i];
		}
		else
		{
			sum += arr[i];
		}
		if (sum > max)
		{
			max = sum;
		}
	}
	return max;
}


int main()
{
	int arr[] = { 1, -2, 3, 10, -4, 7, 2, -5 };
	int len = sizeof(arr) / sizeof(arr[0]);

	int ret = FindMax2(arr, len);
	printf("%d\n", ret);
	return 0;
}

#endif

//十
#if 0
char FindOnceMin(char *str)
{
	int arr[256] = { 0 };
	int i = 0;
	char *p = str;
	while (*str != '\0')
	{
		arr[(*str)]++;
		str++;
	}
	str = p;
	while (*str!='\0')
	{
		if (arr[*str] == 1)
		{
			return *str;

		}
		else
			str++;
	}
	return 0;
}
int main()
{
	char str[] = "bbajjjacc";
	char b = FindOnceMin(str);
	printf("%c\n", b);
	return 0;
}

#endif

//十一
#if 0
char FindOnceMin(char *str)
{
	int arr[256] = { 0 };
	int i = 0;
	char *p = str;
	while (*str != '\0')
	{
		arr[(*str)]++;
		str++;
	}
	str = p;
	while (*str != '\0')
	{
		if (arr[*str] == 2)
		{
			return *str;

		}
		else
			str++;
	}
	return 0;
}
int main()
{
	char str[] = "qywyer12tdd";
	char b = FindOnceMin(str);
	printf("%c\n", b);
	return 0;
}


#endif

//十二
#if 0
void FindOnce(int *arr,int len)
{
	int i = 0;
	int ret = 0;
	int count = 0;
	int a = 0;
	int b = 0;
	for (i = 0; i < len; i++)
	{
		ret ^= arr[i];
	}
	for (i = 0; i < 32; i++)
	{
		if (ret >> i == 1)
		{
			break;
		}
		count++;

	}
	for (i = 0; i < len; i++)
	{
		if (arr[i] >> count == 1)
		{
			a ^= arr[i];
		}
		if (arr[i] >> count == 0)
		{
			b ^= arr[i];
		}
	}
	printf("%d,%d\n", a, b);
}


int main()
{
	int arr[] = { 1, 3, 5, 7, 1, 3, 5, 9 };
	int len = sizeof(arr) / sizeof(arr[0]);
	FindOnce(arr,len);
	return 0;
}

#endif


//十三
#if 0
char *DeleteRepeateChar(char *str1, char *str2)
{
	char *p = str1;
	char *q = str1;
	char arr[256] = { 0 };
	while (*str2 != '\0')
	{
		arr[*str2]++;
		str2++;
	}
	while (*p != '\0')
	{
		if (arr[*p] == 0)
		{
			*q = *p;
			q++;

		}
	
		p++;
	}
	*q = '\0';
	return str1;
}
int main()
{
	char str1[] = "wolcome to cvte";
	char str2[] = "come";
	printf("%s\n", DeleteRepeateChar(str1, str2));
	return 0;
}
#endif

//十四
#if 0
int IfHuiWen(int k)
{
	int tmp = k;
	int sum = 0;
	while (k != 0)
	{
		sum = sum * 10 + k % 10;
		k /= 10;
	}
	if (sum == tmp)
	{
		return 1;
	}
	return 0;
}

int main()
{
	


	printf("%d\n", IfHuiWen(121));
	printf("%d\n", IfHuiWen(1221));
	printf("%d\n", IfHuiWen(1211));
	return 0;
}
#endif

//十五
#if 0
Node *FindMidleNode(pList plist)
{
	Node *pFast = plist;
	Node *pSlow = plist;

	while(pFast != NULL)
	{
		if(pFast->next != NULL)
			pFast = pFast->next->next;

		pSlow = pSlow->next;
	}

}

#endif

//十六
//实现pow(x,y)
#if 0
int myPow(int x, int y)
{
	int sum = 0;
	int tmp = 0;
	if (y == 0)
	{
		return 1;
	}
	tmp = myPow(x, y / 2);

	if (y > 0)
	{
		if ((y & 1) == 0)
		{
			sum = tmp*tmp;
		}
		else
		{
			sum = x*tmp*tmp;
		}
	}
	return sum;
}


int main()
{
	int x = 2;
	int y = 3;
	int ret = myPow(x, 1);
	printf("%d\n", ret);
	return 0;
}


#endif

//十七
#if 0

int *twosum(int *arr, int k, int *num1, int *num2)
{
	int *a = (int *)malloc(sizeof(int)* 2);
	while (*num1 + *num2 != k)
	{
		if (*num1 + *num2 > k)
		{
			num2--;
		}
		else if (*num1 + *num2 < k)
		{
			num1++;
		}
	}
	arr = num1;
	++arr;
	arr = num2;
	return num1;
	
}
int main()
{

	return 0;
}


#endif

//十八
#if 0
int Check(int n)
{
	int i = 0;
	for (i; i < 32; i++)
	{
		if ((1 << i) == n)
		{
			return 1;
		}
	}
	return 0;
}

int main()
{

	printf("%d\n", Check(13));
	return 0;
}
#endif

//十九
#if 0
char *StrCompress(char *str)
{
	int count = 0;
	assert(str != NULL);
	char *p = str;
	char *q = str;
	while (*q != '\0')
	{
		count = 1;
		while (q[0] == q[1])
		{
			count++;
			q++;
		}
		if (count == 1)
		{
			*p++ = *q++;

		}
		else
		{
			*p = count+'0';
			p += 2;
			q++;
		}
	}
	*p = '\0';
	return str;
}

int main()
{
	char str[] = "xaaikh";
	printf("%s\n", StrCompress(str));
	return 0;
}
#endif

//二十  删除多余的空格

#if 1

char *DeBlank(char *arr)
{
	int flag = 0;
	int p = 0;
	int q = 0;
	while (arr[q] != '\0')
	{
		if (!flag && arr[q] == ' ')
		{
			q++;
		}
		else if (arr[q] != ' ')
		{
			flag = 1;
			arr[p++] = arr[q++];
		}
		else if (flag)
		{
			flag = 0;
			arr[p++] = arr[q++];
		}
	}
	if (arr[p - 1] == ' ')
	{
		arr[p - 1] = '\0';
	}
	else
	{
		arr[p] = '\0';
	}
	return arr;
}
int main()
{
	char arr[] = "   abc   de f  ";
	
	printf("%s\n", DeBlank(arr));
	//printf("%d\n", strlen(arr));

	return 0;
}
#endif