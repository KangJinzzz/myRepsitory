#define _CRT_SECURE_NO_WARNINGS 1
#include"Contact.h"
//初始化通讯录
void InitContact(Contact *pCon) //初始化
{
	int i = 0;
	assert(pCon != NULL);
	pCon->capticty = DAFAULT_SIZE;
	pCon->usedSize = 0;
	pCon->per = (PersonInfo *)malloc(sizeof(struct PersonInfo)*pCon->capticty);
	assert(pCon->per != NULL);

	memset(pCon->per, 0, sizeof(struct PersonInfo)*pCon->capticty);
}

static int CheckFull(Contact *pCon)
{
	PersonInfo *ptr = NULL;
	assert(pCon != NULL);
	if (pCon->usedSize == pCon->capticty)
	{
		ptr = (PersonInfo*)realloc(pCon->per,2 * (sizeof(PersonInfo)*pCon->capticty));
		if (ptr != NULL)
		{
			pCon->per = ptr;
			pCon->capticty *= 2;
			printf("增容成功！\n");
			return 1;
		}
		else
		{
			printf("增容失败！");
			return 0;
		}
	}
	return 1;

}
//添加
void AddContact(Contact *pCon)
{
	Load(pCon);
	int ret = CheckFull(pCon);
	assert(pCon != NULL);

	if (ret == 1)
	{
		printf("请输入姓名：\n");
		scanf("%s", pCon->per[pCon->usedSize].name);
		printf("请输入性别：\n");
		scanf("%s", pCon->per[pCon->usedSize].sex);
		printf("请输入年龄：\n");
		scanf("%d", &(pCon->per[pCon->usedSize].age));
		printf("请输入电话：\n");
		scanf("%s", pCon->per[pCon->usedSize].tele);
		printf("请输入住址：\n");
		scanf("%s", pCon->per[pCon->usedSize].addr);

		pCon->usedSize++;
	}

}
//根据姓名查找
int SearchContact(Contact *pCon)
{
	char Nam[MAX_NAME];
	int i = 0;
	assert(pCon != NULL);
	if (pCon->usedSize == 0)
	{
		return -1;
	}
	printf("请输入姓名：\n");
	scanf("%s", Nam);
	for (i = 0; i < pCon->usedSize; i++)
	{
		if (strcmp(Nam,pCon->per[i].name)==0)
		{
			return i;
		}
	}
	return -1;
}
//删除
void DelContact(Contact *pCon)
{
	int i = 0;
	int index = SearchContact(pCon);
	assert(pCon != NULL);
	if (-1 == index)
	{
		printf("没有此人！\n");
		return;
	}

	for (i = index; i < pCon->usedSize-1; i++)
	{
		pCon->per[i] = pCon->per[i + 1];
	}
	pCon->usedSize--;
	printf("删除成功！\n");

}
//打印
void ShowContact(Contact *pCon)
{
	int i = 0;
	assert(pCon != NULL);
	printf("%-20s %-10s %-10s %-11s %-15s\n", "姓名", "性别", "年龄", "电话", "地址");
	for (i = 0; i < pCon->usedSize;i++)
	{
		printf("%-20s % -10s % -10d % -11s % -15s\n", pCon->per[i].name, pCon->per[i].sex, 
			pCon->per[i].age, pCon->per[i].tele, pCon->per[i].addr);
	}
}
//修改
void ModifyContact(Contact *pCon)
{
	int i = 0;
	int ret = SearchContact(pCon);
	assert(pCon != NULL);
	if (-1 == ret)
	{
		return;
	}
	printf("请输入姓名：\n");
	scanf("%s", pCon->per[ret].name);
	printf("请输入性别：\n");
	scanf("%s", pCon->per[ret].sex);
	printf("请输入年龄：\n");
	scanf("%d", &(pCon->per[ret].age));
	printf("请输入电话：\n");
	scanf("%s", pCon->per[ret].tele);
	printf("请输入住址：\n");
	scanf("%s", pCon->per[ret].addr);
}

//清空
void ClearContact(Contact *pCon)
{
	pCon->usedSize = 0;
}
//按名字排序
void SortContact(Contact *pCon)
{
	int i = 0;
	int j = 0;
	int count = 1;
	PersonInfo tmp;
	assert(pCon != NULL);
	for (i = 0; i < pCon->usedSize - 1; i++)
	{
		count = 1;
		for (j = 0; j < pCon->usedSize - i - 1; j++)
		{
			if (strcmp(pCon->per[j].name, pCon->per[j + 1].name)>0)
			{
				tmp = pCon->per[j];
				pCon->per[j] = pCon->per[j + 1];
				pCon->per[j + 1] = tmp;
				count = 0;
			}
		}
		if (1 == count)
		{
			break;
		}
	}
}

void DestoryContact(Contact *pCon)
{
	assert(pCon != NULL);
	free(pCon->per);
	pCon->per = NULL;
	pCon->capticty = 0;
	pCon->usedSize = 0;
}

void Save(Contact *pCon)
{
	//PersonInfo tmp;
	int i = 0;
	assert(pCon != NULL);
	FILE *pf = fopen("text6.txt", "wb");
	if (pf != NULL)
	{
		fwrite(pCon->per, sizeof(PersonInfo), pCon->usedSize, pf);
		//for (i = 0; i < pCon->usedSize; i++)
		//{
		//	tmp = pCon->per[i];
		//	fwrite(&tmp, sizeof(PersonInfo), 1, pf);
		//}

	}
	fclose(pf);
	pf = NULL;
}

void Load(Contact *pCon)
{
	PersonInfo tmp = {0};
	int i = 0;
	int count = 0;
	assert(pCon != NULL);
	FILE *pf = fopen("text6.txt", "rb");
	if (pf != NULL)
	{

		while (fread(&tmp, sizeof(PersonInfo), 1, pf))
		{
			CheckFull(pCon);
			pCon->per[pCon->usedSize++] = tmp;
		}

	}

	fclose(pf);
	pf = NULL;
}



