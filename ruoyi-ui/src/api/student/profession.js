import request from '@/utils/request'

// 查询专业列表
export function listProfession(query) {
  return request({
    url: '/student/profession/list',
    method: 'get',
    params: query
  })
}

// 查询专业详细
export function getProfession(id) {
  return request({
    url: '/student/profession/' + id,
    method: 'get'
  })
}

// 新增专业
export function addProfession(data) {
  return request({
    url: '/student/profession',
    method: 'post',
    data: data
  })
}

// 修改专业
export function updateProfession(data) {
  return request({
    url: '/student/profession',
    method: 'put',
    data: data
  })
}

// 删除专业
export function delProfession(id) {
  return request({
    url: '/student/profession/' + id,
    method: 'delete'
  })
}
