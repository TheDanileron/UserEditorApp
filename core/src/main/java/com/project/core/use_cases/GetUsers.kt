package com.project.core.use_cases

import com.project.core.data.UserRepository

class GetUsers(val uRepository: UserRepository) {
    suspend operator fun invoke() = uRepository.getList()
}