import request from '@/utils/request'

// 查询院系列表
export function listDepartment(query) {
  return request({
    url: '/student/department/list',
    method: 'get',
    params: query
  })
}

// 查询院系详细
export function getDepartment(id) {
  return request({
    url: '/student/department/' + id,
    method: 'get'
  })
}

// 新增院系
export function addDepartment(data) {
  return request({
    url: '/student/department',
    method: 'post',
    data: data
  })
}

// 修改院系
export function updateDepartment(data) {
  return request({
    url: '/student/department',
    method: 'put',
    data: data
  })
}

// 删除院系
export function delDepartment(id) {
  return request({
    url: '/student/department/' + id,
    method: 'delete'
  })
}
