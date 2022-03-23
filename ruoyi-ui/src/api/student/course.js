import request from '@/utils/request'

// 查询课程信息列表
export function listCourse(query) {
  return request({
    url: '/student/course/list',
    method: 'get',
    params: query
  })
}

// 查询课程信息详细
export function getCourse(id) {
  return request({
    url: '/student/course/' + id,
    method: 'get'
  })
}

// 新增课程信息
export function addCourse(data) {
  return request({
    url: '/student/course',
    method: 'post',
    data: data
  })
}

// 修改课程信息
export function updateCourse(data) {
  return request({
    url: '/student/course',
    method: 'put',
    data: data
  })
}

// 删除课程信息
export function delCourse(id) {
  return request({
    url: '/student/course/' + id,
    method: 'delete'
  })
}
//通过院系ID查询课程
export function getCourseListByDepartmentId(departmentId) {
  return request({
    url: '/student/course/listByDepartmentId/' + departmentId,
    method: 'get'
  })
}
