import request from '@/utils/request'

// 查询课程性质列表
export function listNature(query) {
  return request({
    url: '/student/nature/list',
    method: 'get',
    params: query
  })
}

// 查询课程性质详细
export function getNature(id) {
  return request({
    url: '/student/nature/' + id,
    method: 'get'
  })
}

// 新增课程性质
export function addNature(data) {
  return request({
    url: '/student/nature',
    method: 'post',
    data: data
  })
}

// 修改课程性质
export function updateNature(data) {
  return request({
    url: '/student/nature',
    method: 'put',
    data: data
  })
}

// 删除课程性质
export function delNature(id) {
  return request({
    url: '/student/nature/' + id,
    method: 'delete'
  })
}
