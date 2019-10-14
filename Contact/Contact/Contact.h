#ifndef _CONTACT_H_
#define _CONTACT_H_
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include<string.h>
#define MAX_NAME 20
#define MAX_TEL 11
#define MAX_ADDR 15
#define MAX_SEX 3
#define MAX_NUMPERSON 1000

#define DAFAULT_SIZE 2

enum Option
{
	Exit,
	add,
	search,
	del,
	show,
	modify,
	clear,
	sort,
	destory
};

typedef struct PersonInfo
{
	char name[MAX_NAME];
	char sex[MAX_SEX];
	short age;
	char tele[MAX_TEL];
	char addr[MAX_ADDR];
}PersonInfo;

typedef struct Contact
{
	//PersonInfo per[MAX_NUMPERSON]; 
	PersonInfo *per;
	int usedSize;//被使用的个数
	int capticty;  //容量
}Contact;


void InitContact(Contact *pCon);
void AddContact(Contact *pCon);
int SearchContact(Contact *pCon);
void DelContact(Contact *pCon);
void ShowContact(Contact *pCon);
void ClearContact(Contact *pCon);
void SortContact(Contact *pCon);
void ModifyContact(Contact *pCon);
void DestoryContact(Contact *pCon);
void Save(Contact *pCon);
void Load(Contact *pCon);


#endif