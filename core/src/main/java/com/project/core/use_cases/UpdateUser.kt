package com.project.core.use_cases

import com.project.core.data.UserRepository
import com.project.core.domain.User

class UpdateUser(val uRepository: UserRepository) {
    suspend operator fun invoke(updatedUser: User): Result<User?> {
       return uRepository.update(updatedUser)
    }
}