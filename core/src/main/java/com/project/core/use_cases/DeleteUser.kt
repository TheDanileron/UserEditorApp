package com.project.core.use_cases

import com.project.core.data.UserRepository

class DeleteUser(val uRepository: UserRepository) {
    suspend operator fun invoke(id: Int): Result<Boolean> {
        return uRepository.delete(id)
    }
}