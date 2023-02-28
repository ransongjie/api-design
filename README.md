# 接口设计
唯一，见名知意
请求参数: Query参数, Path参数, Body参数
# 增加
|Operation|Method|Api|
|---|---|---|
|增加一个|Post|`/student/one`|
|批量增加|Post|`/student/batch`|
# 删除
|Operation|Method|Api|
|---|---|---|
|根据ID删除|Delete|`/student/one/{id}`|
|根据ID列表批量删除|Delete|`/student/batch/{ids}`|
批量删除：
- `http://localhost:8080/student/batch/2,3,4`
# 修改
|Operation|Method|Api|
|---|---|---|
|根据ID修改|Put|`/student/one`|
|根据ID列表批量修改|Put|`/student/batch`|
# 查询
|Operation|Method|Api|
|---|---|---|
|根据ID查询|Get|`/student/{id}`|
|分页查询|Get|`/student/page/{pageNum}/{pageSize}`|
|查询所有|Get|`/student/all`|
|根据条件查询|Get|`/student/list`|
分页查询：
- `http://localhost:8080/student/page/1/3`