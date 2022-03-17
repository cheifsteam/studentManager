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
