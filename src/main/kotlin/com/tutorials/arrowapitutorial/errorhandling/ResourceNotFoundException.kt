package com.tutorials.arrowapitutorial.errorhandling

data class ResourceNotFoundException(override val message: String) : Exception(message)