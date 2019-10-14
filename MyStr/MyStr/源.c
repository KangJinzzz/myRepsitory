#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include<string.h>

unsigned int MyStrlen(const char *str)
{
	int count = 0;
	assert(str != NULL);
	while (*str != '\0')
	{
		count++;
		str++;
	}
	return count;
}

char *MyStrcpy(char *arr, const char *str)
{
	char *p = arr;
	assert((arr != NULL) && (str != NULL));
	while (*arr++ = *str++);
	return p;
}

char *MyStrcat(char *dest, const char *src)
{
	char *p = dest;
	assert(dest != NULL && src != NULL);
	while (*dest != '\0')
	{
		dest++;
	}
	while (*dest++ = *src++);
	return p;
}

int MyStrcmp(const char *str1, const char*str2)
{
	assert((str1 != NULL) && (str2 != NULL));
	while (*str1 == *str2)
	{
		if (*str1 == '\0')
		{
			return 0;
		}
		str1++;
		str2++;
	}
	if (*str1 > *str2)
	{
		return 1;
	}
	else
	{
		return -1;
	}
}
char *MyStrncpy(char *dest, const char *src, int n)
{
	int i = 0;
	char *p = dest;
	assert(dest != NULL && src != NULL);
	for (i = 0; i < n; i++)
	{
		*dest++ = *src++;
	}
	//*dest = '\0';
	return p;
}

char *MyStrncat(char *dest, const char *src, int n)
{
	char *p = dest;
	int i = 0;
	assert(dest != NULL && src != NULL);
	while (*dest != '\0')
	{
		dest++;
	}
	for (i = 0; i < n; i++)
	{
		*dest++ = *src++;
	}
	*dest = '\0';
	return p;
}

int MyStrncmp(const char *str1, const char*str2, int n)
{
	int count = 0;
	int diff = 0;
	assert((str1 != NULL) && (str2 != NULL));
	while (count < n && ((diff = (*str1 - *str2)) == 0) && *str1 != '\0')
	{
		str1++;
		str2++;
		count++;
	}
	return diff;
}

const char *MyStrstr(const char *str1, const char*str2)
{
	const char *s1 = str1;
	const char *s2 = str2;
	const char *start = str1;
	assert((str1 != NULL) && (str2 != NULL));
	while (*start != '\0')
	{
		s1 = start;
		s2 = str2;
		while (*s1 != '\0' && *s2 != '\0' && *s1 == *s2)
		{
			s1++;
			s2++;
		}
		if (*s2 == '\0')
		{
			return start;
		}
		if (*s1 == '\0')
		{
			return NULL;
		}
		start++;
	}
}
void *MyMemcpy(void *dest, const void *src, size_t count)
{
	unsigned int i = 0;
	char *p = dest;
	assert(dest != NULL&&src != NULL);
	while (i++ < count)
	{
		*(char *)dest = *(char *)src;
		((char *)dest)++;
		((char *)src)++;
	}
	return p;
}
void *MyMemmove(void *dest, const void *src, size_t count)
{
	unsigned int i = 0;
	char *p = dest;
	assert(dest != NULL&&src != NULL);
	if (src < dest)
	{
		dest = ((char *)dest) - 1 + count;
		src = ((char *)src) - 1 + count;
		while (i++ < count)
		{

			*(char *)dest = *(char *)src;
			((char *)dest)--;
			((char *)src)--;
		}
	}
	else
	{
		i = 0;
		while (i++ < count)
		{
			*(char *)dest = *(char *)src;
			((char *)dest)++;
			((char *)src)++;
		}
	}
	return p;
}
int main()
{
	int ret = 5;
	char arr[20] = "adefbbc";
	char *str = "bbc";

	system("pause");
	return 0;

}
