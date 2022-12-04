package com.blogspot.jvalentino.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import org.apache.commons.rng.UniformRandomProvider
import org.apache.commons.rng.simple.RandomSource

/**
 * <p>A Task.</p>
 * @author jvalentino2
 */
@SuppressWarnings(['Println'])
class RandomTask extends DefaultTask {

    String group = 'demo'
    String description = 'Generates a random number'

    @TaskAction
    void perform() {
        UniformRandomProvider rng = RandomSource.create(RandomSource.MT)

        double random = rng.nextDouble()
        println "RANDOM: ${random}"
    }
}
