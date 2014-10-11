#!/usr/bin/env python
# -*- coding: utf-8 -*-

settings = {
    '_project_': 'park',
    '_Project_': 'Park',
    '_company_': 'inno',
    'company': 'inno',
    'Company': 'Inno',
    '_output_': 'E:\\stuff\\sample\\gen',
    '_mysql_': {
        'host': '127.0.0.1',
        'port': 3306,
        'user': 'root',
        'passwd': '123456'
    },
    '_modules_': {
        'catalog': {
            'db': 'inno_park',
            'tables': ['catalog_bizarea', 'catalog_biztype', 'catalog_degree',
                        'catalog_emp','catalog_person','catalog_relation',
                        'catalog_spendtype','catalog_stage']
        },
        'proposal': {
            'db': 'inno_park',
            'tables': ['field', 'permission', 'proposal',
                        'proposal_detail', 'proposal_ext', 'proposal_file',
                        'proposal_member', 'proposal_notice', 'workflow', 'workflow_approve'
                        ]
        },
        'project': {
            'db': 'inno_park',
            'tables': ['project', 'outcome', 'outcome_file']
        },
        'expert': {
            'db': 'inno_park',
            'tables': ['expert', 'expert_skill', 'expert_work']
        },
        'person': {
            'db': 'inno_park',
            'tables': ['person', 'person_family', 'person_school']
        },
        'company': {
            'db': 'inno_park',
            'tables': ['company', 'company_member', 'spend', 'spend_file']
        },
        'news': {
            'db': 'inno_park',
            'tables': ['kit', 'post', 'post_content', 'post_file']
        },
        'system': {
            'db': 'inno_park',
            'tables': ['sys_user']
        }
    }
}
