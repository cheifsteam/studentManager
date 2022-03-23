// 查询院系列表
import request from "@/utils/request";

export function listAllDepartment() {
  return request({
    url: '/student/department/listAll',
    method: 'get'

  })
}
// 查询专业详细
export function getProfessionByDepartId(departmentId) {
  return request({
    url: '/student/profession/' + departmentId,
    method: 'post'
  })
}

// 查询专业列表
export function listAllProfession(query) {
  return request({
    url: '/student/profession/listAll',
    method: 'get',
    param : query
  })
}
//通过专业ID查询专业
export function getProfessionByProfessId(professionId) {
  return request({
    url: '/student/profession/info/' + professionId,
    method: 'get'
  })
}
//通过院系ID查询班级

export function getClazzByDepartId(departmentId) {
  return request({
    url: '/student/clazz/queryInfo/' + departmentId,
    method: 'get'
  })
}

//通过专业ID查询班级
export function getClazzByProfessId(professionId) {
  return request({
    url: '/student/clazz/query/' + professionId,
    method: 'get'
  })
}

